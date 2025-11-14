package ar.edu.unrc.dc.entities;


public class EmptyCell implements Entity {
    private static final char SYMBOL = '_';
    private TEntity type;
    private Position position;

    public EmptyCell(int row, int column) {
        this.position = new Position(row, column);
        //this.type = Tentity.EMPTY;
         
    }

    @Override
    public TEntity getType() {
        return type;
    }

    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
        public void destroy() {
        }
    

    @Override
    public boolean isDestroyed() {
        return false;
    }



}
