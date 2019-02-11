package a.asd.shooterclicker.standard;

import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Weapon;
import a.asd.shooterclicker.patterns.Generator;

public class WeaponImpl implements Weapon {
    private long baseDamage;
    private double attackSpeed;
    private String rarity;
    private String name;
    private String model;
    private int itemLevel;

    public WeaponImpl(){}

    @Override
    public long getBaseDamage() {
        return baseDamage;
    }

    @Override
    public double getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public String getRarity() {
        return rarity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void generateWeapon(String rarity, int itemLevel) {
        this.rarity = rarity;

        int lowend;
        int highend;

        if(rarity.equals(GameConstants.RARITY_UNCOMMON)){
            lowend = lowEnd(GameConstants.COMMON_WEAPON_BASE_DAMAGE,itemLevel);
            highend = highEnd(GameConstants.COMMON_WEAPON_BASE_DAMAGE,itemLevel);
            name = GameConstants.UNCOMMON_NAMES[Generator.generateRandom(0,9)];
        }
        else if(rarity.equals(GameConstants.RARITY_RARE)){
            lowend = lowEnd(GameConstants.RARE_WEAPON_BASE_DAMAGE,itemLevel);
            highend = highEnd(GameConstants.RARE_WEAPON_BASE_DAMAGE,itemLevel);
            name = GameConstants.RARE_NAMES[Generator.generateRandom(0,9)];

        }
        else if(rarity.equals(GameConstants.RARITY_EPIC)){
            lowend = lowEnd(GameConstants.EPIC_WEAPON_BASE_DAMAGE,itemLevel);
            highend = highEnd(GameConstants.EPIC_WEAPON_BASE_DAMAGE,itemLevel);
            name = GameConstants.EPIC_NAMES[Generator.generateRandom(0,9)];

        }
        else if(rarity.equals(GameConstants.RARITY_LEGENDARY)){
            lowend = lowEnd(GameConstants.LEGENDARY_WEAPON_BASE_DAMAGE,itemLevel);
            highend = highEnd(GameConstants.LEGENDARY_WEAPON_BASE_DAMAGE,itemLevel);
            name = GameConstants.LEGENDARY_NAMES[Generator.generateRandom(0,9)];
        }
        else{
            lowend = lowEnd(GameConstants.COMMON_WEAPON_BASE_DAMAGE,itemLevel);
            highend = highEnd(GameConstants.COMMON_WEAPON_BASE_DAMAGE,itemLevel);
            name = GameConstants.COMMON_NAMES[Generator.generateRandom(0,9)];
        }

        baseDamage = Generator.generateRandom(lowend,highend);

        int lowAttackSpeed = 5;
        int highAttackSpeed = 50;

        attackSpeed = Generator.generateRandom(lowAttackSpeed,highAttackSpeed)/10;

        if(attackSpeed == 0){
            baseDamage = (baseDamage * Generator.generateRandom(20,40)) / 10;
        }

        if(attackSpeed == 0){
            this.model = GameConstants.HEAVY_SNIPER;
        } else if(attackSpeed < 4){
            this.model = GameConstants.RIFLE;
        } else {
            this.model = GameConstants.MINI_GUN;
        }

        setItemLevel(itemLevel);
    }

    public void setBaseDamage(long baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "WeaponImpl{" +
                "baseDamage=" + baseDamage +
                ", attackSpeed=" + attackSpeed +
                ", rarity='" + rarity + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    public static int lowEnd(int base,int itemLevel){
        return (itemLevel*base) - (int) ((itemLevel*base)*0.1f);
    }

    public static int highEnd(int base,int itemLevel){
        return (itemLevel*base) + (int) ((itemLevel*base)*0.1f);
    }

    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public int getWorth(){
        int worth = 0;
        worth = (int)(baseDamage * Math.pow(2,attackSpeed));
        return worth;
    }
}
