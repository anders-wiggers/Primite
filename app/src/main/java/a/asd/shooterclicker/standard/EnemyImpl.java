package a.asd.shooterclicker.standard;

import android.app.Activity;

import java.util.ArrayList;

import a.asd.shooterclicker.activities.MainActivity;
import a.asd.shooterclicker.framework.DamageType;
import a.asd.shooterclicker.framework.Enemy;
import a.asd.shooterclicker.framework.EnemyStrategies.HealthStrategy;
import a.asd.shooterclicker.framework.EnemyStrategies.LootStrategy;
import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Weapon;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.Damage.Damage;
import a.asd.shooterclicker.standard.Damage.Defendants;

public class EnemyImpl implements Enemy {
    private final long fullHealth;
    private long health;
    private String name;
    private Player player;
    private long worth;
    private long experience;
    private Defendants[] defendants;
    private ArrayList<Buff> buffs = new ArrayList<>();
    private EnemyInternalStats enemyInternalStats = new EnemyInternalStats();

    //Strategies
    private HealthStrategy healthStrategy;
    private LootStrategy lootStrategy;


    public EnemyImpl(String name, HealthStrategy healthStrategy, LootStrategy lootStrategy, Defendants... defendances){
        this.name = name;
        this.player = Game.getInstance().getPlayer();
        this.defendants = defendances;
        this.healthStrategy = healthStrategy;
        this.lootStrategy = lootStrategy;

        worth = generateWorth();
        health = generateHealth();
        fullHealth = health ;
        experience = generateExperience();
    }

    public long getFullHealth(){
        return fullHealth;
    }

    private long generateHealth() {
        return healthStrategy.generateHealth();
    }

    private long generateWorth(){
        int x = Generator.generateRandom(1,10);
        long reward = (long) ( -4.057559 + 7.0387* x - 2.374462 * Math.pow(x,2) + 0.4334998 * Math.pow(x,3) - 0.04183968 * Math.pow(x,4) + 0.001660138 * Math.pow(x,5))*player.getLevel();
        reward += Generator.generateRandom(0,10);
        return reward;
    }

    private long generateExperience(){
        long xp = player.getLevel()*25;
        return xp;
    }

    @Override
    public long getWorth() {
        return worth;
    }

    @Override
    public long getHealth() {
        return health;
    }

    public int[] getDamageValues(){
        return damageValues;
    }

    //for debugging
    private int[] damageValues = new int[5];

    public boolean damageEnemy(Damage damage, Activity... activities){
        int damageToEnemy = 0;

        int i = 0;
        for(DamageType d : DamageType.values()) {
            int bonusDamage = 0;
            boolean hasSpecialDamageOccurred = false;

            for(int b = 0; b<defendants.length;b++){
                if(d.equals(defendants[b].getDamageType())) {
                    bonusDamage += damage.getDamage(d) * defendants[b].getModifier();
                    hasSpecialDamageOccurred = true;
                }

            }

            if  (!hasSpecialDamageOccurred){
                int damageToBeDealt = (int)(damage.getDamage(d)* enemyInternalStats.getNormalDamageTaken());
                damageToEnemy += damageToBeDealt;
                damageValues[i] = damageToBeDealt;

            } else {
                bonusDamage = (int)(bonusDamage * enemyInternalStats.getNormalDamageTaken());
                damageToEnemy += bonusDamage ;
                damageValues[i] = bonusDamage;
            }

            i++;
        }

        if(damage.getBuff() != null) addBuff(damage.getBuff());

        if(activities.length != 0) {
            if (null != activities[0]) ((MainActivity) activities[0]).showDamage(damageToEnemy);
        }

        health -= damageToEnemy;

        if(health<0){
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public long getExperience() {
        return experience;
    }

    @Override
    public Weapon getLoot() {
        return lootStrategy.getLoot();
    }

    public int getHealthPercent() {
        double percent = ((1.0f*health)/(1.0f*fullHealth))*100.0;
        return (int) percent;
    }

    @Override
    public String toString() {
        return "EnemyImpl{" +
                "fullHealth=" + fullHealth +
                ", health=" + health +
                ", name='" + name + '\'' +
                ", player=" + player +
                ", worth=" + worth +
                ", experience=" + experience +
                '}';
    }

    public void addBuff(Buff buff){
        buffs.add(buff);
        applyBuffs();
    }

    private void applyBuffs() {
        for(Buff b : buffs){
            b.effect(this);
        }
    }

    public EnemyInternalStats changeInnerStats(){
        return enemyInternalStats;
    }
}
