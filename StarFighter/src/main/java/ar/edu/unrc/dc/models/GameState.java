package ar.edu.unrc.dc.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.edu.unrc.dc.models.entities.Entity;
import ar.edu.unrc.dc.models.entities.ProjectileFactory;
import ar.edu.unrc.dc.models.entities.Starfighter;

public class GameState {
    private Starfighter starfighter;
    private Board board;
    private List<Entity> projectiles;
    private List<ProjectileMovement> projectileMovements;
    private boolean gameOver;

    public GameState() {
        this.starfighter = new Starfighter();
        this.board = new Board(1, 1);
        this.board.placeEntity(starfighter.getRow(), starfighter.getColumn(), Board.STARFIGHTER);
        this.projectiles = new ArrayList<>();
        this.gameOver = false;
        this.projectileMovements = new ArrayList<>();
    }

    public GameState(Starfighter starfighter, Board board, List<Entity> projectiles, boolean gameOver,
            List<ProjectileMovement> movements) {
        this.starfighter = starfighter;
        this.board = board;
        this.board.placeEntity(starfighter.getRow(), starfighter.getColumn(), Board.STARFIGHTER);
        this.projectiles = projectiles;
        this.gameOver = gameOver;
        this.projectileMovements = movements;
    }

    public GameState(int row, int col) {
        initializationStarFighter(row, col);
        this.board = new Board(row, col);
        this.board.placeEntity(starfighter.getRow(), starfighter.getColumn(), Board.STARFIGHTER);
        this.projectiles = new ArrayList<>();
        this.gameOver = false;
        this.projectileMovements = new ArrayList<>();
    }

    private void initializationStarFighter(int row, int col) {
        int newRow;
        int newCol = 0;
        if (row % 2 == 0) {
            newRow = (row / 2) - 1;
        } else {
            newRow = row / 2;
        }
        this.starfighter = new Starfighter(newRow, newCol);
    }

    public Starfighter getStarfighter() {
        return starfighter;
    }

    public void setStarfighter(Starfighter starfighter) {
        this.starfighter = starfighter;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Entity> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(List<Entity> projectiles) {
        this.projectiles = projectiles;
    }

    public void addProjectile(Entity projectile) {
        int projectileRow = projectile.getRow();
        int projectileCol = projectile.getColumn();
        if (board.isWithinBounds(projectileRow, projectileCol)) {

            this.projectiles.add(projectile);

        }

    }

    public void setProjectileMovements(List<ProjectileMovement> movements) {
        this.projectileMovements = movements;
    }

    public List<ProjectileMovement> getProjectileMovements() {
        return projectileMovements;
    }

    public boolean checkColision() {
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (board.getCellContent(i, j) == 'X') {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setGameOver(int row, int col) {
        this.gameOver = true;
        this.board.placeEntity(row, col, board.COLLISION);
    }

    @Override
    public GameState clone() {
        ProjectileFactory factory = new ProjectileFactory();

        Starfighter sfCopy = new Starfighter(starfighter.getRow(), starfighter.getColumn());
        if (starfighter.isDestroyed()) {
            sfCopy.destroy();
        }

        Board boardCopy = board.clone();

        List<Entity> projectilesCopy = new ArrayList<>();
        for (Entity p : projectiles) {
            Entity projectileCopy = factory.createProjectile(p.getRow(), p.getColumn());
            projectilesCopy.add(projectileCopy);
        }

        List<ProjectileMovement> projectileMovementsCopy = new ArrayList<>();
        

        GameState cloned = new GameState(sfCopy, boardCopy, projectilesCopy, gameOver, projectileMovementsCopy);

        return cloned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof GameState other))
            return false;

        return gameOver == other.gameOver
                && Objects.equals(starfighter, other.getStarfighter())
                && Objects.equals(board, other.getBoard())
                && Objects.equals(projectiles, other.getProjectiles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameOver, starfighter, board, projectiles, projectileMovements);
    }

}
