package a.asd.shooterclicker.standard.Damage;

import a.asd.shooterclicker.framework.DamageType;

public class Defendants {
    private DamageType damageType;
    private Double modifier;

    public Defendants(DamageType damageType, Double modifier) {
        this.damageType = damageType;
        this.modifier = modifier;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public Double getModifier() {
        return modifier;
    }
}
