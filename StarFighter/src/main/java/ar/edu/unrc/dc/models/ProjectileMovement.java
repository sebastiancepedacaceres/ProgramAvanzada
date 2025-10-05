package ar.edu.unrc.dc.models;

public class ProjectileMovement {
    
    private final int fromRow;
    private final int fromCol;
    private final int toRow;
    private final int toCol;
    private final boolean collision;
    
    public ProjectileMovement(int fromRow, int fromCol, int toRow, int toCol, boolean collision) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.collision = collision;
    }
    
    public int getFromRow() { return fromRow; }
    public int getFromCol() { return fromCol; }
    public int getToRow() { return toRow; }
    public int getToCol() { return toCol; }
    public boolean isCollision() { return collision; }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProjectileMovement other = (ProjectileMovement) obj;
        return fromRow == other.fromRow &&
               fromCol == other.fromCol &&
               toRow == other.toRow &&
               toCol == other.toCol &&
               collision == other.collision;
    }
}
