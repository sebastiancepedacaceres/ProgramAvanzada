package main.java.ar.edu.unrc.dc.entities.character;

/**
 * Tipos de armas disponibles en el setup.
 * Cada arma modifica stats y define comportamiento de disparo.
 */
public enum WeaponType {
    STANDARD,   // 1 proyectil, avanza 5 espacios/turno
    SPREAD,     // 3 proyectiles diagonales, avanzan 1 espacio/turno
    SNIPE,      // 1 proyectil que teleporta 8 espacios
    ROCKET,     // 2 proyectiles que doblan velocidad (1, 2, 4, 8...)
    SPLITTER    // 1 proyectil estático
}