package a.asd.shooterclicker.standard.Strategies.EnemyStrategies;

import a.asd.shooterclicker.framework.EnemyStrategies.BuffStrategy;
import a.asd.shooterclicker.standard.EnemyImpl;
import a.asd.shooterclicker.standard.EnemyInternalStats;

public class SniperDeBuff implements BuffStrategy {

    private EnemyImpl enemy;
    private EnemyInternalStats enemyInternalStats;

    @Override
    public void effect(EnemyImpl enemy) {
        this.enemy = enemy;
        enemyInternalStats = enemy.changeInnerStats();
        enemyInternalStats.setNormalDamageTaken(1.5);
    }
}
