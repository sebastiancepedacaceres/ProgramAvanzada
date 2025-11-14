package ar.edu.unrc.dc.entities.character;

import ar.edu.unrc.dc.entities.Position;
import ar.edu.unrc.dc.entities.TEntity;

/**
 * Implementación base del Starfighter SIN equipamiento.
 * Esta clase tiene stats mínimos que serán modificados por decoradores.
 * 
 * Stats base (sin equipamiento):
 * - Health: 50 / 50
 * - Energy: 30 / 30
 * - Regen: 1 HP/turno, 1 Energy/turno
 * - Armour: 0
 * - Vision: 5
 * - Move: 3
 * - MoveCost: 3
 * - FireCost: 0 (sin arma no puede disparar)
 * - ProjectileDamage: 0
 */
public class Starfighter implements Character {
    
    // ========================================
    // CONSTANTES - STATS BASE
    // ========================================
    private static final int BASE_MAX_HEALTH = 50;
    private static final int BASE_MAX_ENERGY = 30;
    private static final int BASE_HEALTH_REGEN = 1;
    private static final int BASE_ENERGY_REGEN = 1;
    private static final int BASE_ARMOUR = 0;
    private static final int BASE_VISION = 5;
    private static final int BASE_MAX_MOVE = 3;
    private static final int BASE_MOVE_COST = 3;
    
    // ========================================
    // ESTADO MUTABLE
    // ========================================
    private Position position;
    private final Position spawnPosition;
    private int currentHealth;
    private int currentEnergy;
    private boolean destroyed;
    
    // ========================================
    // CONSTRUCTOR
    // ========================================
    
    /**
     * Crea un Starfighter base en la posición de spawn
     * @param spawnRow Fila donde aparece (ceiling(rows/2))
     * @param spawnCol Columna donde aparece (siempre columna 0)
     */
    public Starfighter(int spawnRow, int spawnCol) {
        this.spawnPosition = new Position(spawnRow, spawnCol);
        this.position = new Position(spawnRow, spawnCol);
        this.currentHealth = BASE_MAX_HEALTH;
        this.currentEnergy = BASE_MAX_ENERGY;
        this.destroyed = false;
    }
    
    // ========================================
    // IMPLEMENTACIÓN DE Entity
    // ========================================
    
    @Override
    public TEntity getType() {
        return TEntity.STARFIGHTER;
    }
    
    @Override
    public Position getPosition() {
        return position;
    }
    
    @Override
    public void destroy() {
        this.destroyed = true;
        this.currentHealth = 0;
    }
    
    @Override
    public boolean isDestroyed() {
        return destroyed;
    }
    
    // ========================================
    // STATS DE COMBATE
    // ========================================
    
    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }
    
    @Override
    public int getMaxHealth() {
        return BASE_MAX_HEALTH;
    }
    
    @Override
    public void setCurrentHealth(int health) {
        this.currentHealth = health;
        if (this.currentHealth <= 0) {
            this.currentHealth = 0;
            destroy();
        }
    }
    
    @Override
    public int getCurrentEnergy() {
        return currentEnergy;
    }
    
    @Override
    public int getMaxEnergy() {
        return BASE_MAX_ENERGY;
    }
    
    @Override
    public void setCurrentEnergy(int energy) {
        this.currentEnergy = Math.max(0, energy);
    }
    
    @Override
    public int getHealthRegen() {
        return BASE_HEALTH_REGEN;
    }
    
    @Override
    public int getEnergyRegen() {
        return BASE_ENERGY_REGEN;
    }
    
    @Override
    public int getArmour() {
        return BASE_ARMOUR;
    }
    
    // ========================================
    // MOVIMIENTO Y VISIÓN
    // ========================================
    
    @Override
    public int getVision() {
        return BASE_VISION;
    }
    
    @Override
    public int getMaxMove() {
        return BASE_MAX_MOVE;
    }
    
    @Override
    public int getMoveCost() {
        return BASE_MOVE_COST;
    }
    
    // ========================================
    // COMBATE - DISPARO
    // ========================================
    
    @Override
    public int getFireCost() {
        return 0; // Sin arma, no puede disparar
    }
    
    @Override
    public int getProjectileDamage() {
        return 0; // Sin arma, no hace daño
    }
    
    @Override
    public WeaponType getWeaponType() {
        return null; // Sin arma equipada
    }
    
    // ========================================
    // POSICIÓN Y SPAWN
    // ========================================
    
    @Override
    public Position getSpawnPosition() {
        return spawnPosition;
    }
    
    @Override
    public void moveTo(Position newPosition) {
        this.position = newPosition;
    }
    
    // ========================================
    // ENERGÍA - OPERACIONES
    // ========================================
    
    @Override
    public boolean consumeEnergy(int amount) {
        if (currentEnergy >= amount) {
            currentEnergy -= amount;
            return true;
        }
        return false;
    }
    
    @Override
    public void addEnergy(int amount) {
        currentEnergy += amount;
        // Nota: puede exceder máximo con Overcharge
    }
    
    // ========================================
    // SALUD - OPERACIONES
    // ========================================
    
    @Override
    public void takeDamage(int damage) {
        setCurrentHealth(currentHealth - damage);
    }
    
    @Override
    public void heal(int amount) {
        currentHealth = Math.min(currentHealth + amount, getMaxHealth());
        // Nota: Repair puede hacer que exceda el máximo
    }
    
    @Override
    public void regenerate() {
        // Regeneración de salud (solo si no excede el máximo)
        if (currentHealth <= getMaxHealth()) {
            currentHealth = Math.min(currentHealth + getHealthRegen(), getMaxHealth());
        }
        
        // Regeneración de energía (solo si no excede el máximo)
        if (currentEnergy <= getMaxEnergy()) {
            currentEnergy = Math.min(currentEnergy + getEnergyRegen(), getMaxEnergy());
        }
    }
    
    // ========================================
    // EQUIPAMIENTO - INFO (base no tiene equipamiento)
    // ========================================
    
    @Override
    public ArmourType getArmourType() {
        return null;
    }
    
    @Override
    public EngineType getEngineType() {
        return null;
    }
    
    @Override
    public PowerType getPowerType() {
        return null;
    }
}