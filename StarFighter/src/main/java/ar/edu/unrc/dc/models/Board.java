package ar.edu.unrc.dc.models;

public class Board {

    private final int rows;
    private final int columns;
    private final char[][] grid;

    public static final char EMPTY = '_';
    public static final char STARFIGHTER = 'S';
    public static final char PROJECTILE = '*';
    public static final char COLLISION = 'X';

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new char[rows][columns];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                grid[r][c] = EMPTY;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    public boolean isEmpty(int row, int col) {
        if (!isWithinBounds(row, col)) {
            return false;
        }
        return grid[row][col] == EMPTY;
    }

    public void placeEntity(int row, int col, char symbol) {
        if (!isWithinBounds(row, col)) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        grid[row][col] = symbol;
    }

    public char getCellContent(int row, int col) {
        if (!isWithinBounds(row, col)) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        return grid[row][col];
    }

    public void clearCell(int row, int col) {
        if (!isWithinBounds(row, col)) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        grid[row][col] = EMPTY;
    }

    public void clearBoard() {
        initializeGrid();
    }

    @Override
    public Board clone() {
        Board copy = new Board(this.rows, this.columns);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                copy.grid[r][c] = this.grid[r][c]; 
            }
        }
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        if (this == o)
            return true;

        Board board = (Board) o;
        if (rows != board.rows || columns != board.columns)
            return false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (this.grid[r][c] != board.grid[r][c])
                    return false;
            }
        }

        return true;
    }

}
