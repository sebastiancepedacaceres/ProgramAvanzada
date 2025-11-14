package ar.edu.unrc.dc.models.commands.sfcommands;
import ar.edu.unrc.dc.entities.Position;
import ar.edu.unrc.dc.entities.character.Starfighter;
import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.commands.Command;

/**
 * Command Pattern
 * Command to move the starfighter within the game board.
 */
public class MoveCommand implements Command {

    
    public MoveCommand(Position position) {
    }

    /**
     * returns a new GameState with the starfighter moved according to the command.
     * If the move exceeds maxMove, an IllegalArgumentException is thrown.
     * If a collision occurs with a projectile, the starfighter is destroyed and the game is marked as over.
     */
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
                if (gameState.checkCollisionAt(newGameState, r, currentCol)) {
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
            gameState.handleCollision(newGameState, collisionRow, collisionCol);
            return newGameState;
        }
        
        currentRow = targetRow;
        
        //Movimiento Horizontal
        if (horizontalMove != 0) {
            int step = (horizontalMove > 0) ? 1 : -1;
            for (int c = currentCol + step; ; c += step) {
                if (gameState.checkCollisionAt(newGameState, currentRow, c)) {
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
            gameState.handleCollision(newGameState, collisionRow, collisionCol);
            return newGameState;
        }
        
        sf.setPosition(targetRow, targetCol);
        board.placeEntity(targetRow, targetCol, Board.STARFIGHTER);
        
        return newGameState;
    }
 
    
    
}
