package a.asd.shooterclicker;

import org.junit.Before;
import org.junit.Test;

import a.asd.shooterclicker.framework.DamageType;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.standard.Damage.Damage;
import a.asd.shooterclicker.standard.Damage.Defendants;
import a.asd.shooterclicker.standard.EnemyImpl;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.StandardHealth;
import a.asd.shooterclicker.standard.WeaponImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestDamage {
    Damage d;
    PlayerImpl pl;

    @Before
    public void setup(){
        d = new Damage();
        pl = new PlayerImpl();
        pl.setLevel(10);
    }

    @Test
    public void shouldBeDamageingEnemyCorrect20whenNoWeakness(){
        d.addDamage(DamageType.NORMAL,20);

        EnemyImpl e =  new EnemyImpl(pl,"john", new StandardHealth(pl));

        e.damageEnemy(d);

        assertThat(e.getDamageValues()[0],is(20));
    }

    @Test
    public void shouldBeDamageingEnemyCorrect30whenWeaknessToNormal(){
        d.addDamage(DamageType.NORMAL,20);

        Defendants df = new Defendants(DamageType.NORMAL,1.5);

        EnemyImpl e =  new EnemyImpl(pl,"john", new StandardHealth(pl),df);

        e.damageEnemy(d);

        assertThat(e.getDamageValues()[0],is(30));
    }

    @Test
    public void shouldBeDamageingEnemyCorrect10whenStrongAgainstNormal(){
        d.addDamage(DamageType.NORMAL,20);

        Defendants df = new Defendants(DamageType.NORMAL,0.5);

        EnemyImpl e =  new EnemyImpl(pl,"john", new StandardHealth(pl),df);

        e.damageEnemy(d);

        assertThat(e.getDamageValues()[0],is(10));
    }

    @Test
    public void shouldBeDamageingEnemyCorrect0whenImmuneAgainstNormal(){
        d.addDamage(DamageType.NORMAL,20);

        Defendants df = new Defendants(DamageType.NORMAL,0.0);

        EnemyImpl e =  new EnemyImpl(pl,"john", new StandardHealth(pl),df);

        e.damageEnemy(d);

        assertThat(e.getDamageValues()[0],is(0));
    }

    @Test
    public void shouldBeDamageingEnemyCorrectWhenImmuneAgainstNormalButTakesFrostDamage(){
        d.addDamage(DamageType.NORMAL,20);
        d.addDamage(DamageType.FROST,20);

        Defendants df = new Defendants(DamageType.NORMAL,0.0);
        Defendants df1 = new Defendants(DamageType.FROST,2.0);

        EnemyImpl e =  new EnemyImpl(pl,"john", new StandardHealth(pl),df,df1);

        e.damageEnemy(d);

        assertThat(e.getDamageValues()[0],is(0));
        assertThat(e.getDamageValues()[1],is(40));

    }

    @Test
    public void playerShouldBeDamageingCorrectWithWeapon(){
        WeaponImpl wp = new WeaponImpl();
        wp.generateWeapon(GameConstants.RARITY_LEGENDARY,1);

        pl.setCurrentWeapon(wp);

        Defendants df = new Defendants(DamageType.NORMAL,1.0);
        Defendants df1 = new Defendants(DamageType.FROST,2.0);

        EnemyImpl e =  new EnemyImpl(pl,"john", new StandardHealth(pl),df,df1);

        e.damageEnemy(pl.dealDamage());

        assertThat(e.getDamageValues()[0],is((int) wp.getBaseDamage()));
    }

    @Test
    public void playerShouldBeDamageingCorrectWithWeaponAndFrostTalents(){
        WeaponImpl wp = new WeaponImpl();
        wp.generateWeapon(GameConstants.RARITY_LEGENDARY,1);

        pl.setCurrentWeapon(wp);
        pl.changeStats().setPercentFrost(1.0);

        Defendants df = new Defendants(DamageType.NORMAL,1.0);
        Defendants df1 = new Defendants(DamageType.FROST,2.0);

        EnemyImpl e =  new EnemyImpl(pl,"john", new StandardHealth(pl),df,df1);

        long fullHealth = e.getHealth();

        e.damageEnemy(pl.dealDamage());

        assertThat(e.getDamageValues()[0],is((int) wp.getBaseDamage()));
        assertThat(e.getDamageValues()[1],is((int) wp.getBaseDamage()*2));

        assertThat(e.getHealth(),is(fullHealth - (wp.getBaseDamage()+wp.getBaseDamage()*2)));
    }


    @Test
    public void PlayerShouldBeDealing15percentExtraRifleDamage(){
        WeaponImpl wp = new WeaponImpl();
        wp.generateWeapon(GameConstants.RARITY_LEGENDARY,1);

        wp.setModel(GameConstants.RIFLE);

        pl.setCurrentWeapon(wp);
        pl.changeStats().setPercentDamageToRifle(1.15);


        EnemyImpl e =  new EnemyImpl(pl,"john", new StandardHealth(pl));

        e.damageEnemy(pl.dealDamage());

        assertThat(e.getDamageValues()[0],is((int) (wp.getBaseDamage()*1.15)));
    }

    @Test
    public void ShouldBePrintingTestRight(){

        double critmodifyer = Game.getInstance().getPlayer().changeStats().getCritModifier();
        critmodifyer = critmodifyer * 100;
        int inted = (int) critmodifyer;
        String crit = inted+"%";
        System.out.print("crit modifyer: "+ crit    );
    }

}