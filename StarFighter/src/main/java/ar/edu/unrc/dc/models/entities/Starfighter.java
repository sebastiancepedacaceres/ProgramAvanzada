package ar.edu.unrc.dc.models.entities;

import java.util.Objects;

public class Starfighter extends Entity {

    public Starfighter() {
        super();
    }

    public Starfighter(int row, int column) {
        super(row,column);
    }

    public void setPosition(int row, int column) {
        this.row = row;
        this.col = column;
    }

    public void destroy() {
        this.destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || !(o instanceof Starfighter))
            return false;
        Starfighter starfighter = (Starfighter) o;

        if (starfighter.getColumn() == col && starfighter.getRow() == row
                && starfighter.isDestroyed() == destroyed) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row, destroyed);
    }
}
