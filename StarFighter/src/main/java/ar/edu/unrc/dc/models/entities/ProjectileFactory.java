package ar.edu.unrc.dc.models.entities;
public class ProjectileFactory implements EntityFactory {
    
    public Entity createProjectile(int row, int col) {
        return new Projectile(row, col);
    }
}