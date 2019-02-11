package a.asd.shooterclicker.standard.Strategies;

import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Strategies.DamageDealer;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.WeaponImpl;

public class CritDamage implements DamageDealer {

    private final WeaponImpl weapon;
    private final Player player;

    private DamageDealer standardDamage;

    public CritDamage(WeaponImpl weapon, Player player) {
        this.weapon = weapon;
        this.player = player;
        standardDamage = new StandardDamage(weapon,player);
    }

    @Override
    public int dealDamage() {
        int damage = standardDamage.dealDamage();
        if(Generator.generateRandom(0,100)+1<((PlayerImpl)player).changeStats().getCritChance()) {
            damage = (int)(damage * ((PlayerImpl) player).changeStats().getCritModifier());
        }
        return damage;
    }
}
