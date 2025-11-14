package ar.edu.unrc.dc.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.edu.unrc.dc.entities.Entity;
import ar.edu.unrc.dc.entities.character.Starfighter;


/**
 * Class representing the state of the game, including the starfighter, board,
 * projectiles, and game status.
 */

public class GameState {
    private Entity starfighter;
    private Board board;
    private List<Entity> friendlyProjectiles;
     private List<Entity> enemyProjectiles;
    private boolean gameOver;

    public GameState() {
        this.starfighter = new Starfighter(0, 0);
        this.board = new Board(1, 1);
        this.board.placeEntity(starfighter);
        this.friendlyProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.gameOver = false;
    }

    public GameState(Entity starfighter, Board board, List<Entity> friendlyProjectiles, List<Entity> enemyProjectiles, boolean gameOver) {
        this.starfighter = starfighter;
        this.board = board;
        this.board.placeEntity(starfighter);
        this.friendlyProjectiles = friendlyProjectiles;
        this.enemyProjectiles = enemyProjectiles;
        this.gameOver = gameOver;
    }

    public GameState(int row, int col) {
        initializationStarFighter(row, col);
        this.board = new Board(row, col);
        this.board.placeEntity(starfighter);
        this.friendlyProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.gameOver = false;
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

    public Entity getStarfighter() {
        return starfighter;
    }

    public void setStarfighter(Entity starfighter) {
        this.starfighter = starfighter;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Entity> getFriendlyProjectiles() {
        return friendlyProjectiles;
    }

    public void setFriendlyProjectiles(List<Entity> friendlyProjectiles) {
        this.friendlyProjectiles = friendlyProjectiles;
    }

    public void addFriendlyProjectile(Entity projectile) {
        int projectileRow = projectile.getRow();
        int projectileCol = projectile.getColumn();
        if (board.isWithinBounds(projectileRow, projectileCol)) {

            this.friendlyProjectiles.add(projectile);

        }

    }
    public void addEnemyProjectile(Entity projectile) {
        int projectileRow = projectile.getRow();
        int projectileCol = projectile.getColumn();
        if (board.isWithinBounds(projectileRow, projectileCol)) {

            this.enemyProjectiles.add(projectile);

        }

    }
    public List<Entity> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    public void setEnemyProjectiles(List<Entity> enemyProjectiles) {
        this.enemyProjectiles = enemyProjectiles;
    }

    /*
     * Sets the game over state to true, places a collision marker on the board at
     * the specified row and column, and destroys the starfighter.
     */
    public void setGameOver() {
        this.gameOver = true;
        this.starfighter.destroy();
    }

      public boolean isGameOver() {
        return gameOver;
    }


    @Override
    public GameState clone() {
        ProjectileFactory factory = new ProjectileFactory();

        Starfighter sfCopy = new Starfighter(starfighter.getRow(), starfighter.getColumn());
        if (starfighter.isDestroyed()) {
            sfCopy.destroy();
        }

        Board boardCopy = board.clone();

        List<Entity> friendlyProjectilesCopy = new ArrayList<>();
        for (Entity p : friendlyProjectiles) {
            Entity projectileCopy = factory.createProjectile(p.getRow(), p.getColumn());
            friendlyProjectilesCopy.add(projectileCopy);
        }

        List<Entity> enemyProjectilesCopy = new ArrayList<>();
        for (Entity p : enemyProjectiles) {
            Entity projectileCopy = factory.createProjectile(p.getRow(), p.getColumn());
            enemyProjectilesCopy.add(projectileCopy);
        }

        GameState cloned = new GameState(sfCopy, boardCopy, friendlyProjectilesCopy, enemyProjectilesCopy, gameOver);

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
                && Objects.equals(enemyProjectiles, other.getEnemyProjectiles())
                && Objects.equals(friendlyProjectiles, other.getFriendlyProjectiles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameOver, starfighter, board, enemyProjectiles, friendlyProjectiles);
    }

}

