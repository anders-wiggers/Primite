package a.asd.shooterclicker.standard.Damage;

import a.asd.shooterclicker.framework.DamageType;
import a.asd.shooterclicker.standard.Buff;

public class Damage {
    private int normal;
    private int heat;
    private int frost;
    private int impact;
    private int poison;

    private Buff buff;



    public Damage(){}

    public void addDamage(DamageType damageType, int value){
        if(damageType.equals(DamageType.NORMAL))normal = value;
        if(damageType.equals(DamageType.FROST))frost = value;
        if(damageType.equals(DamageType.HEAT))heat = value;
        if(damageType.equals(DamageType.IMPACT))impact = value;
        if(damageType.equals(DamageType.POISON))poison = value;
    }

    public int getDamage(DamageType damageType){
        int damage = 0;

        if(damageType.equals(DamageType.NORMAL)) damage = normal;
        if(damageType.equals(DamageType.FROST)) damage = frost;
        if(damageType.equals(DamageType.HEAT)) damage = heat;
        if(damageType.equals(DamageType.IMPACT)) damage = impact;
        if(damageType.equals(DamageType.POISON)) damage = poison;

        return damage;
    }

    public Buff getBuff() {
        return buff;
    }

    public void addBuff(Buff buff) {
        this.buff = buff;
    }
}
