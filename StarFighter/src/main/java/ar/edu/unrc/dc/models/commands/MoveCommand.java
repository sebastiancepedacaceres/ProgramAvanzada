package ar.edu.unrc.dc.models.commands;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.entities.Entity;
import ar.edu.unrc.dc.models.entities.Starfighter;

public class MoveCommand implements Command {

    private final int verticalMove;
    private final int horizontalMove;
    private final int maxMove;
    
    public MoveCommand(int verticalMove, int horizontalMove, int maxMove) {
        this.verticalMove = verticalMove;
        this.horizontalMove = horizontalMove;
        this.maxMove = maxMove;
    }
    @Override
    public GameState execute(GameState gameState) {
        
        int totalMove = Math.abs(verticalMove) + Math.abs(horizontalMove);
        if (totalMove > maxMove) {
            throw new IllegalArgumentException("Move exceeds maximum allowed: " + totalMove + " > " + maxMove);
        }

        GameState newGameState = gameState.clone();
        Starfighter sf = newGameState.getStarfighter();
        Board board = newGameState.getBoard();
        
        int currentRow = sf.getRow();
        int currentCol = sf.getColumn();
        
        int targetRow = currentRow + verticalMove;
        int targetCol = currentCol + horizontalMove;

        targetRow = Math.max(0, Math.min(targetRow, board.getRows() - 1));
        targetCol = Math.max(0, Math.min(targetCol, board.getColumns() - 1));
        
        board.clearCell(currentRow, currentCol);
        
        boolean collisionOccurred = false;
        int collisionRow = currentRow;
        int collisionCol = currentCol;
        
        //Movimiento Vertical
        if (verticalMove != 0) {
            int step = (verticalMove > 0) ? 1 : -1;
            for (int r = currentRow + step; ; r += step) {
                if (checkCollisionAt(newGameState, r, currentCol)) {
                    collisionOccurred = true;
                    collisionRow = r;
                    collisionCol = currentCol;
                    break;
                }
                
                if (r == targetRow) {
                    break;
                }
            }
        }
        
        if (collisionOccurred) {
            handleCollision(newGameState, collisionRow, collisionCol);
            return newGameState;
        }
        
        currentRow = targetRow;
        
        //Movimiento Horizontal
        if (horizontalMove != 0) {
            int step = (horizontalMove > 0) ? 1 : -1;
            for (int c = currentCol + step; ; c += step) {
                if (checkCollisionAt(newGameState, currentRow, c)) {
                    collisionOccurred = true;
                    collisionRow = currentRow;
                    collisionCol = c;
                    break;
                }
                
                if (c == targetCol) {
                    break;
                }
            }
        }
        
        if (collisionOccurred) {
            handleCollision(newGameState, collisionRow, collisionCol);
            return newGameState;
        }
        
        sf.setPosition(targetRow, targetCol);
        board.placeEntity(targetRow, targetCol, Board.STARFIGHTER);
        
        return newGameState;
    }
    

    private boolean checkCollisionAt(GameState gameState, int row, int col) {
    for (Entity p : gameState.getProjectiles()) {
            if (p.getRow() == row && p.getColumn() == col) {
                return true;
            }
        }
        return false;
    }
    
    private void handleCollision(GameState gameState, int row, int col) {
        Starfighter sf = gameState.getStarfighter();
        sf.setPosition(row, col);
        sf.destroy();
        gameState.getBoard().placeEntity(row, col, Board.COLLISION);
        gameState.setGameOver(true);
    }
    
}
