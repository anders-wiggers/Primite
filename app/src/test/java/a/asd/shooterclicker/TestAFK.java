package a.asd.shooterclicker;


import org.junit.Before;
import org.junit.Test;

import a.asd.shooterclicker.activities.MainActivity;
import a.asd.shooterclicker.framework.DamageType;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.standard.Damage.Damage;
import a.asd.shooterclicker.standard.Damage.Defendants;
import a.asd.shooterclicker.standard.EnemyImpl;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.NoLoot;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.StandardHealth;
import a.asd.shooterclicker.standard.WeaponImpl;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestAFK {

    private MainActivity mainActivity;
    private PlayerImpl player;


    @Before
    public void setup(){
        mainActivity = new MainActivity();
        player = Game.getInstance().getPlayer();
        player.setLevel(5);
    }

    @Test
    public void shouldReturn0WhenAwayFor0Sec(){
        EnemyImpl enemy = new EnemyImpl("k",new StandardHealth(player),new NoLoot());

        int[] gained = mainActivity.gainedWhileAFK(0,0,enemy);

        assertThat(gained[0],is(0)); //EXP
        assertThat(gained[1],is(0)); //CREDIT
        assertThat(gained[2],is(0)); //KILLED
    }


    @Test
    public void shouldReturn2xXPWhenAwayFor10Sec(){
        EnemyImpl enemy = new EnemyImpl("k",new StandardHealth(player),new NoLoot());

        int[] gained = mainActivity.gainedWhileAFK(5,10,enemy);

        assertThat(gained[0],is((int)(enemy.getExperience()*2))); //EXP
        assertThat(gained[1],is((int)(enemy.getWorth()*2))); //CREDIT
        assertThat(gained[2],is(2)); //KILLED
    }

    @Test
    public void shouldReturn10xXPWhenAwayFor10Sec(){
        EnemyImpl enemy = new EnemyImpl("k",new StandardHealth(player),new NoLoot());

        int[] gained = mainActivity.gainedWhileAFK(1,10,enemy);

        assertThat(gained[0],is((int)(enemy.getExperience()*10))); //EXP
        assertThat(gained[1],is((int)(enemy.getWorth()*10))); //CREDIT
        assertThat(gained[2],is(10)); //KILLED
    }

    @Test
    public void shouldReturn3WhenHealth(){
        EnemyImpl enemy = new EnemyImpl("k",new StandardHealth(player),new NoLoot());
        WeaponImpl weapon = new WeaponImpl();

        assertThat(enemy.getHealth(),is(305L));

        weapon.setAttackSpeed(2);
        weapon.setBaseDamage(50);
        weapon.setModel(GameConstants.RIFLE);

        assertThat(mainActivity.timeToKillEnemy(weapon,enemy),is(3.0));
    }

    @Test
    public void intergrationTestShouldReturn(){
        EnemyImpl enemy = new EnemyImpl("k",new StandardHealth(player),new NoLoot());
        WeaponImpl weapon = new WeaponImpl();
        weapon.setAttackSpeed(2);
        weapon.setBaseDamage(50);
        weapon.setModel(GameConstants.RIFLE);

        int[] gained = mainActivity.gainedWhileAFK(mainActivity.timeToKillEnemy(weapon,enemy),9,enemy);

        assertThat(gained[0],is((int)(enemy.getExperience()*3))); //EXP
        assertThat(gained[1],is((int)(enemy.getWorth()*3))); //CREDIT
        assertThat(gained[2],is(3)); //KILLED

    }

    @Test
    public void shouldNotReturnAbursedNumbersWithLowKillTime(){
        EnemyImpl enemy = new EnemyImpl("k",new StandardHealth(player),new NoLoot());
        WeaponImpl weapon = new WeaponImpl();
        weapon.setAttackSpeed(5);
        weapon.setBaseDamage(5000);
        weapon.setModel(GameConstants.RIFLE);

        int[] gained = mainActivity.gainedWhileAFK(mainActivity.timeToKillEnemy(weapon,enemy),2,enemy);

        assertThat(gained[2],is(163)); //KILLED
    }
}
