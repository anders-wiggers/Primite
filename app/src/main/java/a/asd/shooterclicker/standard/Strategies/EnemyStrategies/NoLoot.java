package a.asd.shooterclicker.standard.Strategies.EnemyStrategies;

import a.asd.shooterclicker.framework.EnemyStrategies.LootStrategy;
import a.asd.shooterclicker.framework.Weapon;

public class NoLoot implements LootStrategy {
    @Override
    public Weapon getLoot() {
        return null;
    }
}
