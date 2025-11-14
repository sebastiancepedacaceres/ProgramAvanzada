package ar.edu.unrc.dc.entities.character;

import ar.edu.unrc.dc.entities.Entity;
import ar.edu.unrc.dc.entities.Position;
import ar.edu.unrc.dc.entities.strategies.MoveStrategy;

/**
 * Interface que extiende Entity y define todas las capacidades del Starfighter.
 * Será implementada por Starfighter y decorada por los decoradores de equipamiento.
 * 
 * Patrón: Decorator Pattern
 * - Permite agregar equipamiento modular (armas, armaduras, motores)
 * - Stats finales = stats base + modificadores de cada decorador
 */
public interface Character extends Entity {
    
    // ========================================
    // ESTADÍSTICAS DE COMBATE
    // ========================================
    
    /**
     * @return Salud actual (0 si destruido)
     */
    int getCurrentHealth();
    
    /**
     * @return Salud máxima (modificada por armadura)
     */
    int getMaxHealth();
    
    /**
     * Modifica la salud actual
     * @param health Nueva cantidad de salud
     */
    void setCurrentHealth(int health);
    
    /**
     * @return Energía actual disponible
     */
    int getCurrentEnergy();
    
    /**
     * @return Energía máxima (modificada por motor)
     */
    int getMaxEnergy();
    
    /**
     * Modifica la energía actual
     * @param energy Nueva cantidad de energía
     */
    void setCurrentEnergy(int energy);
    
    /**
     * @return Regeneración de salud por turno
     */
    int getHealthRegen();
    
    /**
     * @return Regeneración de energía por turno
     */
    int getEnergyRegen();
    
    /**
     * @return Valor de armadura (reduce daño de proyectiles)
     */
    int getArmour();
    
    // ========================================
    // MOVIMIENTO Y VISIÓN
    // ========================================
    
    /**
     * @return Alcance de visión (espacios: vertical + horizontal)
     */
    int getVision();
    
    /**
     * @return Máxima distancia de movimiento por turno
     */
    int getMaxMove();
    
    /**
     * @return Costo de energía por espacio movido
     */
    int getMoveCost();
    
    // ========================================
    // COMBATE - DISPARO
    // ========================================
    
    /**
     * @return Costo de energía para disparar
     */
    int getFireCost();
    
    /**
     * @return Daño base de los proyectiles disparados
     */
    int getProjectileDamage();
    
    /**
     * @return Tipo de arma equipada (para lógica de disparo)
     */
    WeaponType getWeaponType();
    
    // ========================================
    // POSICIÓN Y SPAWN
    // ========================================
    
    /**
     * @return Posición donde spawneó (para poder Recall)
     */
    Position getSpawnPosition();
    
    /**
     * Mueve el Starfighter a una nueva posición
     * @param newPosition Nueva posición
     */
    void moveTo(Position newPosition);
    
    // ========================================
    // ENERGÍA - OPERACIONES
    // ========================================
    
    /**
     * Consume energía si hay suficiente disponible
     * @param amount Cantidad a consumir
     * @return true si se pudo consumir, false si no había suficiente
     */
    boolean consumeEnergy(int amount);
    
    /**
     * Añade energía (usado por Overcharge)
     * @param amount Cantidad a añadir
     */
    void addEnergy(int amount);
    
    // ========================================
    // SALUD - OPERACIONES
    // ========================================
    
    /**
     * Aplica daño al Starfighter
     * @param damage Cantidad de daño a aplicar (ya reducido por armadura)
     */
    void takeDamage(int damage);
    
    /**
     * Cura al Starfighter
     * @param amount Cantidad de curación
     */
    void heal(int amount);
    
    /**
     * Aplica regeneración de salud y energía
     */
    void regenerate();
    
    // ========================================
    // EQUIPAMIENTO - INFO
    // ========================================
    
    /**
     * @return Tipo de armadura equipada
     */
    ArmourType getArmourType();
    
    /**
     * @return Tipo de motor equipado
     */
    EngineType getEngineType();
    
    /**
     * @return Tipo de poder especial equipado
     */
    PowerType getPowerType();
}