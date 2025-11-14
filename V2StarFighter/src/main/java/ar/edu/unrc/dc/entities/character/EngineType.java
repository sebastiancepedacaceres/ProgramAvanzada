package main.java.ar.edu.unrc.dc.entities.character;

/**
 * Tipos de motores disponibles en el setup.
 * Cada motor modifica energía, movimiento, visión y costos.
 */
public enum EngineType {
    BASIC,      // Básico: stats moderados
    ADVANCED,   // Avanzado: mejor energía y movimiento
    QUANTUM     // Cuántico: máxima energía y visión, alto costo movimiento
}