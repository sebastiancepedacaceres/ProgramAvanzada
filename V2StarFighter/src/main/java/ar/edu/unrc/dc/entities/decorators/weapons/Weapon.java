package ar.edu.unrc.dc.entities.decorators.weapons;

import ar.edu.unrc.dc.entities.decorators.StarfighterDecorator;

public abstract class Weapon extends StarfighterDecorator {
    protected int fireCost;

    public void spawn() {
    }

    public int getFireCost() {
        return 0;
    }
}
