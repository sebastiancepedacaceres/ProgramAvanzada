package ar.edu.unrc.dc.models;

import org.junit.jupiter.api.Test;
import ar.edu.unrc.dc.entities.Entity;
import ar.edu.unrc.dc.entities.Tentity;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.easymock.EasyMock.*;


public class BoardTest {
    
    private Board board;
    
    @BeforeEach
    public void setUp() {
        board = new Board(5, 10);
    }
    
    // Tests para constructor e initializeGrid
    @Test
    public void testConstructor_CreatesCorrectDimensions() {
        assertEquals(5, board.getRows());
        assertEquals(10, board.getColumns());
    }
    
    @Test
    public void testConstructor_InitializesAllCellsAsEmpty() {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getColumns(); c++) {
                assertTrue(board.isEmpty(r, c));
            }
        }
    }
    
    // Tests para getRows y getColumns
    @Test
    public void testGetRows_ReturnsCorrectValue() {
        assertEquals(5, board.getRows());
    }
    
    @Test
    public void testGetColumns_ReturnsCorrectValue() {
        assertEquals(10, board.getColumns());
    }
    
    // Tests para isWithinBounds - Todas las ramas
    @Test
    public void testIsWithinBounds_ValidPosition() {
        assertTrue(board.isWithinBounds(0, 0));
        assertTrue(board.isWithinBounds(2, 5));
        assertTrue(board.isWithinBounds(4, 9));
    }
    
    @Test
    public void testIsWithinBounds_NegativeRow() {
        assertFalse(board.isWithinBounds(-1, 5));
    }
    
    @Test
    public void testIsWithinBounds_RowTooLarge() {
        assertFalse(board.isWithinBounds(5, 5));
    }
    
    @Test
    public void testIsWithinBounds_NegativeColumn() {
        assertFalse(board.isWithinBounds(2, -1));
    }
    
    @Test
    public void testIsWithinBounds_ColumnTooLarge() {
        assertFalse(board.isWithinBounds(2, 10));
    }
    
    // Tests para isEmpty - Todas las ramas
    @Test
    public void testIsEmpty_OutOfBounds_ReturnsFalse() {
        assertFalse(board.isEmpty(-1, 5));
        assertFalse(board.isEmpty(5, 5));
        assertFalse(board.isEmpty(2, -1));
        assertFalse(board.isEmpty(2, 10));
    }
    
    @Test
    public void testIsEmpty_EmptyCell_ReturnsTrue() {
        assertTrue(board.isEmpty(2, 5));
    }
    
    @Test
    public void testIsEmpty_NonEmptyCell_ReturnsFalse() {
        Entity mockEntity = createMock(Entity.class);
        expect(mockEntity.getType()).andReturn(Tentity.STARFIGHTER);
        replay(mockEntity);
        
        board.placeEntity(2, 5, mockEntity);
        assertFalse(board.isEmpty(2, 5));
        
        verify(mockEntity);
    }
    
    // Tests para placeEntity - Todas las ramas
    @Test
    public void testPlaceEntity_ValidPosition() {
        Entity mockEntity = createMock(Entity.class);
        replay(mockEntity);
        
        assertDoesNotThrow(() -> board.placeEntity(2, 5, mockEntity));
        assertEquals(mockEntity, board.getCellContent(2, 5));
        
        verify(mockEntity);
    }
    
    @Test
    public void testPlaceEntity_OutOfBounds_ThrowsException() {
        Entity mockEntity = createMock(Entity.class);
        replay(mockEntity);
        
        assertThrows(IllegalArgumentException.class, 
            () -> board.placeEntity(-1, 5, mockEntity));
        assertThrows(IllegalArgumentException.class, 
            () -> board.placeEntity(5, 5, mockEntity));
        assertThrows(IllegalArgumentException.class, 
            () -> board.placeEntity(2, -1, mockEntity));
        assertThrows(IllegalArgumentException.class, 
            () -> board.placeEntity(2, 10, mockEntity));
        
        verify(mockEntity);
    }
    
    // Tests para getCellContent - Todas las ramas
    @Test
    public void testGetCellContent_ValidPosition() {
        Entity mockEntity = createMock(Entity.class);
        replay(mockEntity);
        
        board.placeEntity(2, 5, mockEntity);
        assertEquals(mockEntity, board.getCellContent(2, 5));
        
        verify(mockEntity);
    }
    
    @Test
    public void testGetCellContent_OutOfBounds_ThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> board.getCellContent(-1, 5));
        assertThrows(IllegalArgumentException.class, 
            () -> board.getCellContent(5, 5));
        assertThrows(IllegalArgumentException.class, 
            () -> board.getCellContent(2, -1));
        assertThrows(IllegalArgumentException.class, 
            () -> board.getCellContent(2, 10));
    }
    
    // Tests para clearCell - Todas las ramas
    @Test
    public void testClearCell_ValidPosition() {
        Entity mockEntity = createMock(Entity.class);
        expect(mockEntity.getType()).andReturn(Tentity.STARFIGHTER);
        replay(mockEntity);
        
        board.placeEntity(2, 5, mockEntity);
        assertFalse(board.isEmpty(2, 5));
        
        board.clearCell(2, 5);
        assertTrue(board.isEmpty(2, 5));
        
        verify(mockEntity);
    }
    
    @Test
    public void testClearCell_OutOfBounds_ThrowsException() {
        assertThrows(IllegalArgumentException.class, 
            () -> board.clearCell(-1, 5));
        assertThrows(IllegalArgumentException.class, 
            () -> board.clearCell(5, 5));
        assertThrows(IllegalArgumentException.class, 
            () -> board.clearCell(2, -1));
        assertThrows(IllegalArgumentException.class, 
            () -> board.clearCell(2, 10));
    }
    
    // Tests para clearBoard
    @Test
    public void testClearBoard_ResetsAllCells() {
        Entity mockEntity1 = createMock(Entity.class);
        Entity mockEntity2 = createMock(Entity.class);
        Entity mockEntity3 = createMock(Entity.class);
        replay(mockEntity1, mockEntity2, mockEntity3);
        
        // Colocar varias entidades
        board.placeEntity(0, 0, mockEntity1);
        board.placeEntity(2, 5, mockEntity2);
        board.placeEntity(4, 9, mockEntity3);
        
        // Limpiar el tablero
        board.clearBoard();
        
        // Verificar que todas las celdas están vacías
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getColumns(); c++) {
                assertTrue(board.isEmpty(r, c));
            }
        }
        
        verify(mockEntity1, mockEntity2, mockEntity3);
    }
    
    // Tests para clone
    @Test
    public void testClone_CreatesCopyWithSameDimensions() {
        Board clone = board.clone();
        assertEquals(board.getRows(), clone.getRows());
        assertEquals(board.getColumns(), clone.getColumns());
    }
    
    @Test
    public void testClone_CopiesAllCells() {
        Entity mockEntity = createMock(Entity.class);
        replay(mockEntity);
        
        board.placeEntity(2, 5, mockEntity);
        
        Board clone = board.clone();
        
        assertEquals(board.getCellContent(2, 5), clone.getCellContent(2, 5));
        
        // Verificar que todos los contenidos son iguales
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getColumns(); c++) {
                assertEquals(board.getCellContent(r, c), clone.getCellContent(r, c));
            }
        }
        
        verify(mockEntity);
    }
    
    @Test
    public void testClone_IndependentCopy() {
        Board clone = board.clone();
        
        Entity mockEntity = createMock(Entity.class);
        expect(mockEntity.getType()).andReturn(Tentity.STARFIGHTER);
        replay(mockEntity);
        
        board.placeEntity(2, 5, mockEntity);
        
        // El clon no debe verse afectado
        assertTrue(clone.isEmpty(2, 5));
        
        verify(mockEntity);
    }
    
    // Tests para equals - Todas las ramas
    @Test
    public void testEquals_SameObject_ReturnsTrue() {
        assertTrue(board.equals(board));
    }
    
    @Test
    public void testEquals_NullObject_ReturnsFalse() {
        assertFalse(board.equals(null));
    }
    
    @Test
    public void testEquals_DifferentClass_ReturnsFalse() {
        assertFalse(board.equals("Not a Board"));
        assertFalse(board.equals(new Object()));
    }
    
    @Test
    public void testEquals_DifferentRows_ReturnsFalse() {
        Board other = new Board(3, 10);
        assertFalse(board.equals(other));
    }
    
    @Test
    public void testEquals_DifferentColumns_ReturnsFalse() {
        Board other = new Board(5, 8);
        assertFalse(board.equals(other));
    }
    
    @Test
    public void testEquals_DifferentCellContent_ReturnsFalse() {
        Board other = new Board(5, 10);
        Entity mockEntity = createMock(Entity.class);
        replay(mockEntity);
        
        board.placeEntity(2, 5, mockEntity);
        assertFalse(board.equals(other));
        
        verify(mockEntity);
    }
    
    @Test
    public void testEquals_SameDimensionsAndEmptyCells_ReturnsTrue() {
        Board other = new Board(5, 10);
        assertTrue(board.equals(other));
    }
    
    @Test
    public void testEquals_SameDimensionsAndSameCells_ReturnsTrue() {
        Board other = new Board(5, 10);
        Entity mockEntity = createMock(Entity.class);
        replay(mockEntity);
        
        board.placeEntity(2, 5, mockEntity);
        other.placeEntity(2, 5, mockEntity); // Misma referencia
        
        assertTrue(board.equals(other));
        
        verify(mockEntity);
    }
    
    @Test
    public void testEquals_DifferentCellAtDifferentPositions() {
        Board other = new Board(5, 10);
        Entity mockEntity1 = createMock(Entity.class);
        Entity mockEntity2 = createMock(Entity.class);
        replay(mockEntity1, mockEntity2);
        
        board.placeEntity(1, 3, mockEntity1);
        other.placeEntity(3, 7, mockEntity2);
        
        assertFalse(board.equals(other));
        
        verify(mockEntity1, mockEntity2);
    }
    
    @Test
    public void testEquals_AllCellsDifferent() {
        Board other = new Board(5, 10);
        Entity mockEntity1 = createMock(Entity.class);
        Entity mockEntity2 = createMock(Entity.class);
        replay(mockEntity1, mockEntity2);
        
        board.placeEntity(2, 5, mockEntity1);
        other.placeEntity(2, 5, mockEntity2);
        
        assertFalse(board.equals(other));
        
        verify(mockEntity1, mockEntity2);
    }
}