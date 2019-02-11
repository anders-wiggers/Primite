package a.asd.shooterclicker.standard.Strategies;

import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Strategies.DamageDealer;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.WeaponImpl;

public class StandardDamage implements DamageDealer {

    private final WeaponImpl weapon;
    private final PlayerImpl player;

    public StandardDamage(WeaponImpl weapon, Player player) {
        this.weapon = weapon;
        this.player = (PlayerImpl) player;
    }

    @Override
    public int dealDamage() {
        return (int) weapon.getBaseDamage();
    }
}
