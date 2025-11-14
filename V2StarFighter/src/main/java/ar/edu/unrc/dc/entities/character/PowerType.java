package main.java.ar.edu.unrc.dc.entities.character;

/**
 * Tipos de poderes especiales disponibles en el setup.
 */
public enum PowerType {
    RECALL,         // Teletransporte al spawn
    REPAIR,         // +50 HP (puede exceder máximo)
    OVERCHARGE,     // Convierte HP en energía (1 HP = 2 energy)
    DEPLOY_DRONES,  // Limpia todos los proyectiles
    ORBITAL_STRIKE  // 100 daño a todos los enemigos
}