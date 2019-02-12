package a.asd.shooterclicker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import a.asd.shooterclicker.framework.DamageType;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.standard.Buff;
import a.asd.shooterclicker.standard.Damage.Damage;
import a.asd.shooterclicker.standard.Damage.Defendants;
import a.asd.shooterclicker.standard.EnemyImpl;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.BossLoot;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.SniperDeBuff;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.StandardHealth;
import a.asd.shooterclicker.standard.Talent;
import a.asd.shooterclicker.standard.WeaponImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;


import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.standard.PlayerImpl;


public class TestLoot {

    PlayerImpl pl;

    @Before
    public void setup(){
        pl = new PlayerImpl();
    }

    @Test
    public void shouldHave5PercentCritWithPercision5Points(){
        BossLoot bossLoot = new BossLoot();
        for(int i = 0;i<100;i++) {
            System.out.println("" + bossLoot.getLoot().getRarity());
        }
    }

}
