package ar.edu.unrc.dc.models.entities;

import java.util.Objects;



public class Projectile extends Entity {

    public Projectile(int row, int column) {
        super(row, column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Projectile)) 
            return false;
        Projectile projectile = (Projectile) o;

        if (projectile.getColumn() == col && projectile.getRow() == row) {
            return true;
        }

        return false;

    }
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }


}