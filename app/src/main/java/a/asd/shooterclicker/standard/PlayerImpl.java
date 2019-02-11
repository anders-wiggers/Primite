package a.asd.shooterclicker.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a.asd.shooterclicker.framework.DamageType;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.framework.Observer;
import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Strategies.DamageDealer;
import a.asd.shooterclicker.framework.Weapon;
import a.asd.shooterclicker.standard.Damage.Damage;
import a.asd.shooterclicker.standard.Strategies.CritDamage;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.SniperDeBuff;

public class PlayerImpl implements Player {
    private long experience;
    private int level;
    private long buks;
    private List<WeaponImpl> weapons;
    private Weapon currentWeapon;
    private int skillsPoints;
    private int spendPoints = 0;
    private PlayerStats playerStats = new PlayerStats();

    //stats

    //strategies
    private List<Observer> observerList = new ArrayList<>();
    private DamageDealer damageStrategy;
    private HashMap<String, Talent> talents = new HashMap<>();


    public PlayerImpl() {
        generateTalents();
        updateStats();
    }

    @Override
    public long getExperience() {
        return experience;
    }

    @Override
    public int getLevel() {
        return level;
    }

    private void calculateLevel(){
        if(getExpReq()<experience){
            experience = experience - getExpReq();
            level++;
            skillsPoints++;
            notifyObserver(false);
            calculateLevel();
        }
    }

    @Override
    public long getBuks() {
        return buks;
    }

    @Override
    public long getExpReq() {
        double calculated = ( ( Math.pow(level,2)+level ) / 2 * GameConstants.PLAYER_XP_CONST - (level * GameConstants.PLAYER_XP_CONST));
        return (long) calculated;
    }

    @Override
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(WeaponImpl weapon) {
        currentWeapon = weapon;
    }
    
    @Override
    public Map<String, Talent> getTalents() {
        return talents;
    }

    public void addExperience(long experience){
        this.experience += experience * playerStats.getExpGain();
        calculateLevel();
    }

    public void addBuks(long buks){
        this.buks += buks * playerStats.getBuksGain();
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setBuks(long buks) {
        this.buks = buks;
    }

    public int getSkillsPoints() {
        return skillsPoints;
    }

    public void setSkillsPoints(int skillsPoints) {
        this.skillsPoints = skillsPoints;
    }

    public int getSpendPoints() {
        return spendPoints;
    }

    public void setSpendPoints(int spendPoints) {
        this.spendPoints = spendPoints;
    }

    public int expPercentage() {
        int expPercentage;
        /*
        PlayerImpl p = new PlayerImpl();

        p.setLevel(this.level+1);

        int high = (int) p.getExpReq();
        int low = (int) getExpReq();

        expPercentage = (int)((1.0f*(experience - low)) / (1.0f*(high - low)) * 100.0);
         */

        expPercentage = (int)(((1.0f*experience) / (1.0f*getExpReq())) * 100);

        return expPercentage;
    }

    public void addWeapon(WeaponImpl weapon){
        weapons.add(weapon);
    }

    public List<WeaponImpl> getWeapons() {
        return weapons;
    }

    private void reduceSkillPoint(){
        skillsPoints--;
        spendPoints++;
    }


    public Damage dealDamage(){
        damageStrategy = new CritDamage((WeaponImpl) currentWeapon,this);
        int damage = damageStrategy.dealDamage();

        Damage d = new Damage();

        //ADDING WEAPON BONUS
        if(currentWeapon.getModel().equals(GameConstants.RIFLE)) damage = (int) (damage * playerStats.getPercentDamageToRifle());
        if(currentWeapon.getModel().equals(GameConstants.HEAVY_SNIPER)) damage = (int) (damage * playerStats.getPercentDamageToSniper());
        if(currentWeapon.getModel().equals(GameConstants.MINI_GUN)) damage = (int) (damage * playerStats.getPercentDamageToMini());

        //ADDING DEBUFF
        if(talents.get(GameConstants.TALENTS_EXPOSE_WEAKNESS[0]).isComplete() && currentWeapon.getModel().equals(GameConstants.HEAVY_SNIPER)) d.addBuff(new Buff(new SniperDeBuff()));

        damage = (int) (damage * playerStats.getPercentFlatDamageBoost());
        d.addDamage(DamageType.NORMAL,damage);
        //Elemental Damages
        d.addDamage(DamageType.FROST,(int) (damage * playerStats.getPercentFrost()));
        d.addDamage(DamageType.HEAT,(int) (damage * playerStats.getPercentHeat()));
        d.addDamage(DamageType.IMPACT,(int) (damage * playerStats.getPercentInpact()));
        d.addDamage(DamageType.POISON,(int) (damage * playerStats.getPercentPoison()));

        return d;
    }

    public void setWeapons(List<WeaponImpl> weapons) {
        this.weapons = weapons;
    }

    public void removeBuks(int amountToRemove){
        buks -= amountToRemove;
    }

    private void updateStats(){
        playerStats.rebootStats();
        for(Talent t : getTalents().values()){
            t.effect();
        }
    }

    public void generateTalents(){
        talents.putAll(GameConstants.generateTalentList(this));
    }

    public boolean putTalentPoint(String talent){
        if(skillsPoints>0) {
            if(talents.get(talent).addPoint()) {
                updateStats();
                reduceSkillPoint();
                notifyObserver(true);
                return true;
            }
        }
        return false;
    }

    public PlayerStats changeStats(){
        return playerStats;
    }


    public void addObserver(Observer observer){
        observerList.add(observer);
    }

    public void notifyObserver(boolean update){
        for(Observer o : observerList){
            o.update(update);
        }
    }
    
    public void removeObserver(Observer observer){
        observerList.remove(observer);
    }
}


