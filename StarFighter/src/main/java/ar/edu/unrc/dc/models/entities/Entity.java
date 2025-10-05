package ar.edu.unrc.dc.models.entities;

import java.util.Objects;

public abstract class Entity{
    protected int row;
    protected int col;
    protected boolean destroyed;

    public Entity() {
        this.row = 0;
        this.col = 0;
        this.destroyed = false;
    }
    public Entity(int row, int col) {
        this.row = row;
        this.col = col;
        this.destroyed = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return col;
    }

    public void setColumn(int column) {
        this.col = column;
    }

    public void move(int row, int col) {
        setRow(row);
        setColumn(col);

    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void destroy() {
        this.destroyed = true;
    }

    
}