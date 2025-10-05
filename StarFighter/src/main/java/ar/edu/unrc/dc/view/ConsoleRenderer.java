package ar.edu.unrc.dc.view;

import java.util.Scanner;

import ar.edu.unrc.dc.models.Board;
import ar.edu.unrc.dc.models.GameState;
import ar.edu.unrc.dc.models.ProjectileMovement;


public class ConsoleRenderer {
    private final Scanner scanner;
    
    public ConsoleRenderer() {
        this.scanner = new Scanner(System.in);
    }
 
    public void render(GameState gameState, String commandName, String commandStatus) {

        if (!commandName.equals("Projectiles advance")){
            
            System.out.println("Command issued: " + commandName + " (" + commandStatus + ")");
            renderProjectileMovement(gameState);
            renderBoard(gameState.getBoard());
        }
        if (gameState.isGameOver()) {
                System.out.println("Game over.");
        }
    }
    

    public void renderBoard(Board board) {
        
        System.out.print("  ");
        for (int c = 0; c < board.getColumns(); c++) {
            System.out.print(c);
            if (c < board.getColumns() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
        
       
        for (int r = 0; r < board.getRows(); r++) {
            System.out.print(r + " ");
            for (int c = 0; c < board.getColumns(); c++) {
                System.out.print(board.getCellContent(r, c));
                if (c < board.getColumns() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
    
    public void renderProjectileMovement(GameState gameState) {
        
        for (ProjectileMovement movement : gameState.getProjectileMovements()) {

            if (movement.isCollision()) {
                System.out.println("A projectile moves and collides with the Starfighter: (" 
                    + movement.getFromRow() + "," + movement.getFromCol() + ") -> (" + movement.getToRow() + "," + movement.getToCol() + ")");
            } else {
                System.out.println("A projectile moves: (" 
                    + movement.getFromRow() + "," + movement.getFromCol() + ") -> (" + movement.getToRow() + "," + movement.getToCol() + ")");
            }
        }
       
    }

    public void renderError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }
    
    public void renderMessage(String message) {
        System.out.println(message);
    }
    public void render(GameState gameState) {
        renderBoard(gameState.getBoard());
        
        if (gameState.isGameOver()) {
            System.out.println("Game over.");
        }
    }

    public String readUserCommand() {
        String command;
        do {
            System.out.print("Ingrese comando (fire, move, pass, abort): ");
            command = scanner.nextLine().trim().toLowerCase();
        } while (!command.equals("fire") && !command.equals("move") && !command.equals("pass")
                && !command.equals("abort"));
        return command;
    }

    
     public int readInteger(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); 
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); 
        return value;
    }

    public void close() {
        scanner.close();
    }
}