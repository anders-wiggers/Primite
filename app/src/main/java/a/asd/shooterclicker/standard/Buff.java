package a.asd.shooterclicker.standard;

import a.asd.shooterclicker.framework.EnemyStrategies.BuffStrategy;

public class Buff implements BuffStrategy {


    private BuffStrategy buffStrategy;

    public Buff(BuffStrategy buffStrategy) {
        this.buffStrategy = buffStrategy;
    }

    @Override
    public void effect(EnemyImpl enemy) {
        buffStrategy.effect(enemy);
    }
}
