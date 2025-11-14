package ar.edu.unrc.dc.entities;

public interface Entity {

    
    TEntity getType();

    Position getPosition();
    void destroy();

    boolean isDestroyed();
   
}
