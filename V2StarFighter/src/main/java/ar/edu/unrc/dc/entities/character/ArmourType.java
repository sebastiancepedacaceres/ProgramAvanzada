// ar/edu/unrc/dc/entities/character/ArmourType.java
package main.java.ar.edu.unrc.dc.entities.character;

/**
 * Tipos de armaduras disponibles en el setup.
 * Cada armadura modifica defensa, salud, y puede penalizar movimiento/visión.
 */
public enum ArmourType {
    LIGHT,      // Ligera: poca protección, sin penalizaciones
    MEDIUM,     // Media: protección moderada, penalización leve
    HEAVY,      // Pesada: alta protección, penalización moderada
    ULTRA       // Ultra: máxima protección, penalizaciones altas (opcional)
}