package a.asd.shooterclicker.standard.Strategies.EnemyStrategies;

import a.asd.shooterclicker.framework.EnemyStrategies.HealthStrategy;
import a.asd.shooterclicker.framework.Player;

public class StandardHealth implements HealthStrategy {

    private final Player player;

    public StandardHealth(Player player){
        this.player = player;
    }

    @Override
    public long generateHealth() {
        double l = 50 * player.getLevel()+Math.pow(player.getLevel(),2.5);
        return (long) l;
    }
}
