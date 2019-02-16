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
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.NoLoot;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.SniperDeBuff;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.StandardHealth;
import a.asd.shooterclicker.standard.Talent;
import a.asd.shooterclicker.standard.WeaponImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;


import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.standard.PlayerImpl;

public class TestTalents {

    PlayerImpl pl;

    @Before
    public void setup(){
        pl = new PlayerImpl();
        pl.addExperience(100000000);
    }

    @Test
    public void shouldHave5PercentCritWithPercision5Points(){
        assertThat(pl.changeStats().getCritChance(),is(0));

        assertTrue(pl.getSkillsPoints()>5);

        for (int i = 0 ; i<5 ; i++) {
            pl.putTalentPoint(GameConstants.TALENTS_PRECISION[0]);
        }

        assertThat(pl.getTalents().get(GameConstants.TALENTS_PRECISION[0]).isComplete(),is(true));


        assertThat(pl.changeStats().getCritChance(),is(5));
    }

    @Test
    public void shouldHave25percentExtraRifleDamageWith5point(){
        assertThat(pl.changeStats().getPercentDamageToRifle(),is(1.0));

        assertTrue(pl.getSkillsPoints()>5);

        for (int i = 0 ; i<5 ; i++) {
            pl.putTalentPoint(GameConstants.TALENTS_ASSAULT_TRAINING[0]);
        }
        assertThat(pl.changeStats().getPercentDamageToRifle(),is(1.5));
    }

    @Test
    public void playerShouldDeal50percentExtraDamageWhenHitWithSniperWithSniperTalent(){
        WeaponImpl wp = new WeaponImpl();
        wp.generateWeapon(GameConstants.RARITY_LEGENDARY,1);
        wp.setModel(GameConstants.HEAVY_SNIPER);
        pl.setCurrentWeapon(wp);

        EnemyImpl e =  new EnemyImpl("john", new StandardHealth(pl), new NoLoot());
        e.addBuff(new Buff(new SniperDeBuff()));
        e.damageEnemy(pl.dealDamage());

        assertThat(e.getDamageValues()[0],is((int) (wp.getBaseDamage()*1.5)));
    }

    @Test
    public void playerShouldDeal50percentExtraDamageWhenHitWithSniperWithSniperTalentHasBeenTaken(){
        WeaponImpl wp = new WeaponImpl();
        wp.generateWeapon(GameConstants.RARITY_LEGENDARY,1);
        wp.setModel(GameConstants.HEAVY_SNIPER);
        pl.setCurrentWeapon(wp);

        EnemyImpl e =  new EnemyImpl("john", new StandardHealth(pl), new NoLoot());

        setTalent(GameConstants.TALENTS_DISCIPLINED[0]);
        setTalent(GameConstants.TALENTS_ASSAULT_TRAINING[0]);
        setTalent(GameConstants.TALENTS_SLEIGHT_OF_HAND[0]);
        setTalent(GameConstants.TALENTS_RAPID_FIRE[0]);
        setTalent(GameConstants.TALENTS_LOOTER[0]);
        setTalent(GameConstants.TALENTS_EXPOSE_WEAKNESS[0]);


        assertThat(pl.getTalents().get(GameConstants.TALENTS_EXPOSE_WEAKNESS[0]).isComplete(),is(true));

        e.damageEnemy(pl.dealDamage());
        assertThat(e.getDamageValues()[0],is((int) (wp.getBaseDamage()*1.25)));

        e.damageEnemy(pl.dealDamage());
        assertThat(e.getDamageValues()[0],is((int) (wp.getBaseDamage()*1.25*1.5)));
    }

    @Test
    public void shouldBeAbleToTakeAlmostPerfect40Times(){
        WeaponImpl wp = new WeaponImpl();
        wp.generateWeapon(GameConstants.RARITY_LEGENDARY,1);
        wp.setModel(GameConstants.HEAVY_SNIPER);
        pl.setCurrentWeapon(wp);

        EnemyImpl e =  new EnemyImpl("john", new StandardHealth(pl), new NoLoot());

        setTalent(GameConstants.TALENTS_DISCIPLINED[0]);
        setTalent(GameConstants.TALENTS_ASSAULT_TRAINING[0]);
        setTalent(GameConstants.TALENTS_SLEIGHT_OF_HAND[0]);
        setTalent(GameConstants.TALENTS_RAPID_FIRE[0]);
        setTalent(GameConstants.TALENTS_LOOTER[0]);
        setTalent(GameConstants.TALENTS_EXPOSE_WEAKNESS[0]);
        setTalent(GameConstants.TALENTS_HEAVY[0]);
        setTalent(GameConstants.TALENTS_BLACK_FROST[0]);
        setTalent(GameConstants.TALENTS_RIME_ROUNDS[0]);
        setTalent(GameConstants.TALENTS_COOLHEADED[0]);
        setTalent(GameConstants.TALENTS_TRIGGER_FINGERS[0]);
        setTalent(GameConstants.TALENTS_DEADLY_PRECESSION[0]);
        setTalent(GameConstants.TALENTS_RIFLE_EXPERTISE[0]);
        setTalent(GameConstants.TALENTS_COMPOUND_1080[0]);
        setTalent(GameConstants.TALENTS_MINIGUN_EXPERTISE[0]);
        setTalent(GameConstants.TALENTS_AUTO_CORRECTING_AIM[0]);

        setTalent(GameConstants.TALENTS_ALMOST_PERFECT[0]);

        assertThat(pl.getTalents().get(GameConstants.TALENTS_AUTO_CORRECTING_AIM[0]).isComplete(),is(true));
        assertThat(pl.getTalents().get(GameConstants.TALENTS_ALMOST_PERFECT[0]).getPoint(),is(10));

        setTalent(GameConstants.TALENTS_ALMOST_PERFECT[0]);
        assertThat(pl.getTalents().get(GameConstants.TALENTS_ALMOST_PERFECT[0]).getPoint(),is(20));
    }

    @Test
    public void shouldBeAbleGaining2xCritWithCritTalent(){
        WeaponImpl wp = new WeaponImpl();
        wp.generateWeapon(GameConstants.RARITY_LEGENDARY,1);
        wp.setModel(GameConstants.HEAVY_SNIPER);
        pl.setCurrentWeapon(wp);

        EnemyImpl e =  new EnemyImpl("john", new StandardHealth(pl), new NoLoot());

        setTalent(GameConstants.TALENTS_DISCIPLINED[0]);
        setTalent(GameConstants.TALENTS_ASSAULT_TRAINING[0]);
        setTalent(GameConstants.TALENTS_SLEIGHT_OF_HAND[0]);
        setTalent(GameConstants.TALENTS_RAPID_FIRE[0]);
        setTalent(GameConstants.TALENTS_LOOTER[0]);
        setTalent(GameConstants.TALENTS_EXPOSE_WEAKNESS[0]);
        setTalent(GameConstants.TALENTS_HEAVY[0]);
        setTalent(GameConstants.TALENTS_BLACK_FROST[0]);
        setTalent(GameConstants.TALENTS_RIME_ROUNDS[0]);
        setTalent(GameConstants.TALENTS_COOLHEADED[0]);
        setTalent(GameConstants.TALENTS_TRIGGER_FINGERS[0]);
        setTalent(GameConstants.TALENTS_DEADLY_PRECESSION[0]);
        setTalent(GameConstants.TALENTS_RIFLE_EXPERTISE[0]);
        setTalent(GameConstants.TALENTS_COMPOUND_1080[0]);
        setTalent(GameConstants.TALENTS_MINIGUN_EXPERTISE[0]);

        assertThat(pl.changeStats().getCritChance(), is(25));

        setTalent(GameConstants.TALENTS_AUTO_CORRECTING_AIM[0]);

        assertThat(pl.getTalents().get(GameConstants.TALENTS_AUTO_CORRECTING_AIM[0]).isComplete(),is(true));
        assertThat(pl.changeStats().getCritChance(), is(50));
    }


    private void setTalent(String talent){
        for (int i = 0 ; i<10 ; i++) {
            pl.putTalentPoint(talent);
        }
    }

}
