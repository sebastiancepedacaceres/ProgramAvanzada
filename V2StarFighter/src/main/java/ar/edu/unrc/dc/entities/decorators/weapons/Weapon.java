package ar.edu.unrc.dc.entities.decorators.weapons;

import ar.edu.unrc.dc.entities.character.Character;
import ar.edu.unrc.dc.entities.character.WeaponType;
import ar.edu.unrc.dc.entities.decorators.StarfighterDecorator;

/**
 * Decorador abstracto base para todas las armas.
 * 
 * Las armas pueden modificar CUALQUIER stat (positiva o negativamente):
 * - Health (puede ser + o -)
 * - Energy (puede ser + o -)
 * - Regen (health y energy, puede ser + o -)
 * - Armour (puede ser + o -)
 * - Vision (puede ser + o -)
 * - Move (puede ser + o -)
 * - MoveCost (puede ser + o -)
 * - Fire cost (siempre debe definirse)
 * - Projectile damage (siempre debe definirse)
 * 
 * Esto permite crear armas con trade-offs interesantes:
 * - Arma pesada: +HP, +damage, -move, -energy
 * - Arma ligera: +energy, +move, -HP, -damage
 * - Arma de precisión: +damage, -moveCost, -vision (mira cerrada)
 */
public abstract class Weapon extends StarfighterDecorator {
    
    // Stats de combate (siempre deben definirse)
    protected final int fireCost;
    protected final int projectileDamage;
    protected final WeaponType weaponType;
    
    // Modificadores de stats (pueden ser positivos o negativos)
    protected final int healthModifier;
    protected final int energyModifier;
    protected final int healthRegenModifier;
    protected final int energyRegenModifier;
    protected final int armourModifier;
    protected final int visionModifier;
    protected final int moveModifier;
    protected final int moveCostModifier;
    
    /**
     * Constructor para armas con modificadores flexibles
     * 
     * @param decorated Character a decorar
     * @param healthModifier Modificación a max health (+ o -)
     * @param energyModifier Modificación a max energy (+ o -)
     * @param healthRegenModifier Modificación a health regen (+ o -)
     * @param energyRegenModifier Modificación a energy regen (+ o -)
     * @param armourModifier Modificación a armour (+ o -)
     * @param visionModifier Modificación a vision (+ o -)
     * @param moveModifier Modificación a max move (+ o -)
     * @param moveCostModifier Modificación a move cost (+ o -)
     * @param fireCost Costo de energía para disparar
     * @param projectileDamage Daño base de los proyectiles
     * @param weaponType Tipo de arma (para lógica de disparo)
     */
    protected Weapon(Character decorated,
                     int healthModifier,
                     int energyModifier,
                     int healthRegenModifier,
                     int energyRegenModifier,
                     int armourModifier,
                     int visionModifier,
                     int moveModifier,
                     int moveCostModifier,
                     int fireCost,
                     int projectileDamage,
                     WeaponType weaponType) {
        super(decorated);
        this.healthModifier = healthModifier;
        this.energyModifier = energyModifier;
        this.healthRegenModifier = healthRegenModifier;
        this.energyRegenModifier = energyRegenModifier;
        this.armourModifier = armourModifier;
        this.visionModifier = visionModifier;
        this.moveModifier = moveModifier;
        this.moveCostModifier = moveCostModifier;
        this.fireCost = fireCost;
        this.projectileDamage = projectileDamage;
        this.weaponType = weaponType;
    }
    
    // ========================================
    // SOBRESCRITURA DE STATS MODIFICADOS
    // ========================================
    
    @Override
    public int getMaxHealth() {
        return Math.max(1, decorated.getMaxHealth() + healthModifier);
    }
    
    @Override
    public int getMaxEnergy() {
        return Math.max(1, decorated.getMaxEnergy() + energyModifier);
    }
    
    @Override
    public int getHealthRegen() {
        return Math.max(0, decorated.getHealthRegen() + healthRegenModifier);
    }
    
    @Override
    public int getEnergyRegen() {
        return Math.max(0, decorated.getEnergyRegen() + energyRegenModifier);
    }
    
    @Override
    public int getArmour() {
        return Math.max(0, decorated.getArmour() + armourModifier);
    }
    
    @Override
    public int getVision() {
        return Math.max(1, decorated.getVision() + visionModifier);
    }
    
    @Override
    public int getMaxMove() {
        return Math.max(1, decorated.getMaxMove() + moveModifier);
    }
    
    @Override
    public int getMoveCost() {
        return Math.max(1, decorated.getMoveCost() + moveCostModifier);
    }
    
    @Override
    public int getFireCost() {
        return fireCost;
    }
    
    @Override
    public int getProjectileDamage() {
        return projectileDamage;
    }
    
    @Override
    public WeaponType getWeaponType() {
        return weaponType;
    }
}