package a.asd.shooterclicker.framework;

import java.util.HashMap;
import java.util.Map;

import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.Talent;
import a.asd.shooterclicker.standard.Talents.AttackSpeedTalent;
import a.asd.shooterclicker.standard.Talents.BuksGainedTalent;
import a.asd.shooterclicker.standard.Talents.CritDamageTalent;
import a.asd.shooterclicker.standard.Talents.CritTalent;
import a.asd.shooterclicker.standard.Talents.DoubleCritTalent;
import a.asd.shooterclicker.standard.Talents.ExpGainedTalent;
import a.asd.shooterclicker.standard.Talents.FlatDamageTalent;
import a.asd.shooterclicker.standard.Talents.FrostDamageTalent;
import a.asd.shooterclicker.standard.Talents.GasDamageTalent;
import a.asd.shooterclicker.standard.Talents.HeatDamageTalent;
import a.asd.shooterclicker.standard.Talents.ImpactDamageTalent;
import a.asd.shooterclicker.standard.Talents.MinigunDamageTalent;
import a.asd.shooterclicker.standard.Talents.PoisonDamageTalent;
import a.asd.shooterclicker.standard.Talents.RifleDamageTalent;
import a.asd.shooterclicker.standard.Talents.ShatterDamageTalent;
import a.asd.shooterclicker.standard.Talents.SniperDamageTalent;

public class GameConstants {
    public static final int COMMON_WEAPON_BASE_DAMAGE = 23;
    public static final int UNCOMMON_WEAPON_BASE_DAMAGE = 25;
    public static final int RARE_WEAPON_BASE_DAMAGE = 27;
    public static final int EPIC_WEAPON_BASE_DAMAGE = 30;
    public static final int LEGENDARY_WEAPON_BASE_DAMAGE = 35;

    public static final int COMMON_BASE_PRICE = 180;
    public static final int UNCOMMON_BASE_PRICE = 190;
    public static final int RARE_BASE_PRICE = 205;
    public static final int EPIC_BASE_PRICE = 235;
    public static final int LEGENDARY_BASE_PRICE = 300;

    public static final int LEGENDARY_DROP_CHANCE = 5;
    public static final int EPIC_DROP_CHANCE = 75;
    public static final int BLUE_DROP_CHANCE = 20;

    public static final HashMap<String,Integer> LOOT_TABLE = new HashMap<String, Integer>() {{
        put(RARITY_LEGENDARY, 5);
        put(RARITY_EPIC, 75);
        put(RARITY_RARE, 20);
    }};

    public static final String RARITY_COMMON = "common";
    public static final String RARITY_UNCOMMON = "uncommon";
    public static final String RARITY_RARE = "rare";
    public static final String RARITY_EPIC = "epic";
    public static final String RARITY_LEGENDARY = "legendary";

    public static final int PLAYER_XP_CONST = 99;

    public static final String RIFLE = "Assault Rifle";
    public static final String MINI_GUN = "Mini Gun";
    public static final String HEAVY_SNIPER = "Heavy Sniper";




    public static final String[] COMMON_NAMES = {"Voice Of Insanity",
            "The Pig",
            "Infamy",
            "Roaring Launcher",
            "Vile Repeater",
            "Warrior's Ironbark Rifle",
            "Brutal Bronze Fusil",
            "Injection, Hope Of Poor",
            "Endbringer, Vengeance Of The Plummer",
            "Infamy, Bearer Of The Woodsharft"};
    public static final String[] UNCOMMON_NAMES = {"Cruiser",
            "Allegiance",
            "Orbit",
            "Bloodsurge Repeater",
            "Ferocious Longrifle",
            "Warp Carbon Sniper",
            "Fearful Brass Launcher",
            "Lightningflash, Dispatcher Of Zeal",
            "Extinction, Shotgun Of The Summoner",
            "Sweetie, Wit Of Executions"};
    public static final String[] RARE_NAMES = {"Mighty Mouse",
            "Supremacy",
            "Purifier",
            "Malificent Rifle",
            "Banished Rifle",
            "Ritual Mithril Longrifle",
            "Restored Chromed Rifle",
            "Amnesia, Envoy Of Eternal Bloodlust",
            "Nightfall, Boomstick Of The Lasting Night",
            "Falcon, Shooter Of Stealth"};
    public static final String[] EPIC_NAMES = {"Legacy",
            "Discharge",
            "Reign",
            "Conqueror's Carbine",
            "Peacekeeper's Fusil",
            "Timeworn Carbon Shooter",
            "Champion Bronzed Shooter",
            "Pulse, Dawn Of Blessings",
            "Moonsight, Annihilation Of Ashes",
            "Justice, Incarnation Of Lost Worlds"};
    public static final String[] LEGENDARY_NAMES = {"Windweaver",
            "Ghostwalker",
            "Nightfall",
            "Starligh",
            "Hellfire",
            "TriAnd",
            "Azion",
            "43-null",
            "Legionaire",
            "Eternity"
    };


    public static String[][] talentsButtonMap(){
        String [][] buttonMap = new String[15][4];
        buttonMap[0][0] = GameConstants.TALENTS_PRECISION[0];
        buttonMap[0][1] = GameConstants.TALENTS_ASSAULT_TRAINING[0];
        buttonMap[0][2] = GameConstants.TALENTS_DISCIPLINED[0];
        buttonMap[0][3] = GameConstants.TALENTS_HEAVY[0];
        buttonMap[1][0] = "filler";
        buttonMap[1][1] = GameConstants.TALENTS_SLEIGHT_OF_HAND [0];
        buttonMap[1][2] = "filler";
        buttonMap[2][2] = "filler";
        buttonMap[3][2] = "filler";
        buttonMap[2][1] = "filler";

        buttonMap[8][0] = "filler";
        buttonMap[8][1] = "filler";
        buttonMap[10][1] = "filler";
        buttonMap[11][1] = "filler";
        buttonMap[12][1] = "filler";
        buttonMap[12][2] = "filler";
        buttonMap[12][0] = "filler";


        buttonMap[1][3] = GameConstants.TALENTS_ENLIGHTENMENT[0];
        buttonMap[2][0] = GameConstants.TALENTS_IMPACT_BULLET[0];
        buttonMap[3][1] = GameConstants.TALENTS_RAPID_FIRE[0];
        buttonMap[3][3] = GameConstants.TALENTS_LOOTER[0];
        buttonMap[4][0] = GameConstants.TALENTS_STEADY_NERVES[0];
        buttonMap[4][2] = GameConstants.TALENTS_EXPOSE_WEAKNESS[0];
        buttonMap[5][0] = GameConstants.TALENTS_HELLFIRE[0];
        buttonMap[5][1] = GameConstants.TALENTS_BLACK_FROST[0];
        buttonMap[5][2] = GameConstants.TALENTS_MALIGNANT_FORCE[0];
        buttonMap[5][3] = GameConstants.TALENTS_RUPTURE[0];
        buttonMap[6][0] = GameConstants.TALENTS_WILDFIRE_ROUNDS[0];
        buttonMap[6][1] = GameConstants.TALENTS_RIME_ROUNDS[0];
        buttonMap[6][2] = GameConstants.TALENTS_INFECTED_ROUNDS[0];
        buttonMap[6][3] = GameConstants.TALENTS_PENETRATING_ROUNDS[0];
        buttonMap[7][0] = GameConstants.TALENTS_HUSTLER[0];
        buttonMap[7][1] = GameConstants.TALENTS_COOLHEADED[0];
        buttonMap[7][3] = GameConstants.TALENTS_HARDEN[0];
        buttonMap[8][2] = GameConstants.TALENTS_TRIGGER_FINGERS[0];
        buttonMap[9][0] = GameConstants.TALENTS_BLOODMONEY[0];
        buttonMap[9][1] = GameConstants.TALENTS_DEADLY_PRECESSION[0];
        buttonMap[10][0] = GameConstants.TALENTS_DEVILS_BARTER[0];
        buttonMap[10][2] = GameConstants.TALENTS_RIFLE_EXPERTISE[0];
        buttonMap[10][3] = GameConstants.TALENTS_MINIGUN_EXPERTISE[0];
        buttonMap[11][0] = GameConstants.TALENTS_COMPOUND_1080[0];
        buttonMap[11][2] = GameConstants.TALENTS_FRAGTURE[0];
        buttonMap[12][3] = GameConstants.TALENTS_SNIPER_EXPERTISE[0];
        buttonMap[13][0] = GameConstants.TALENTS_PERFECTING_THE_COMPOUND[0];
        buttonMap[13][1] = GameConstants.TALENTS_AUTO_CORRECTING_AIM[0];
        buttonMap[13][2] = GameConstants.TALENTS_PERFECTING_THE_BLAST[0];
        buttonMap[14][1] = GameConstants.TALENTS_ALMOST_PERFECT[0];


        return buttonMap;
    }

    //Talents
    public static final String[] TALENTS_PRECISION = {"Precision", "Increases your critical hit change by 1% per point"};
    public static final String[] TALENTS_IMPACT_BULLET = {"Impact Bullet", "Increases your critical hit damage by 5% per point"};
    public static final String[] TALENTS_ASSAULT_TRAINING = {"Assault Training", "Increases your damage with Rifles by 10% per point"};
    public static final String[] TALENTS_DISCIPLINED = {"Disciplined", "Increases your damage by Snipers with 5% per point"};
    public static final String[] TALENTS_HEAVY = {"Heavy", "Increases your damage by Minigun with 1% per point"};
    public static final String[] TALENTS_SLEIGHT_OF_HAND = {"Sleight of Hand", "Increases your attack speed by 1% per point"};
    public static final String[] TALENTS_RAPID_FIRE = {"Rapid fire", "Increases your attack speed by 5% per point"};
    public static final String[] TALENTS_ENLIGHTENMENT = {"Enlightenment", "Increases your experience gained by 5% per point"};
    public static final String[] TALENTS_LOOTER = {"Looter", "Increases buks gained from killing enemies by 5% per point"};
    public static final String[] TALENTS_STEADY_NERVES = {"Steady Nerves", "Increases your critical hit change by 5% per point"};
    public static final String[] TALENTS_EXPOSE_WEAKNESS = {"Expose Weakness", "After hitting an enemy with a Sniper shot \n the enemy takes an additionally 50% damage \n from all sources."};
    public static final String[] TALENTS_HELLFIRE = {"Hellfire", "Damage now deal an additionally 20% heat damage"};
    public static final String[] TALENTS_BLACK_FROST = {"Black Frost", "Damage now deal an additionally 20% frost damage"};
    public static final String[] TALENTS_MALIGNANT_FORCE = {"Malignant Force", "Damage now deal an additionally 20% toxic damage"};
    public static final String[] TALENTS_RUPTURE = {"Rupture", "Damage now deal an additionally 20% impact damage"};
    public static final String[] TALENTS_RIME_ROUNDS = {"Rime Rounds", "Increases your frost damage by 1% per point"};
    public static final String[] TALENTS_WILDFIRE_ROUNDS = {"Wildfire Rounds", "Increases your heat damage by 1% per point"};
    public static final String[] TALENTS_INFECTED_ROUNDS = {"Infected Rounds", "Increases your toxic damage by 1% per point"};
    public static final String[] TALENTS_PENETRATING_ROUNDS = {"Penetrating Rounds", "Increases your impact damage by 1% per point"};
    public static final String[] TALENTS_HUSTLER = {"Hustler", "Increases buks rewarded from killing enemies by 5% per point"};
    public static final String[] TALENTS_COOLHEADED = {"Coolheaded", "Increases your critical hit change by 5% per point"};
    public static final String[] TALENTS_HARDEN = {"Harden", "Increases overall damage by 1% per point"};
    public static final String[] TALENTS_TRIGGER_FINGERS = {"Trigger fingers", "Increases your attack speed by 3% per point"};
    public static final String[] TALENTS_BLOODMONEY = {"Bloodmoney", "Doubles buks rewarded from killing enemies"};
    public static final String[] TALENTS_DEADLY_PRECESSION = {"Deadly Precession", "Increases your critical hit damage by 10% per point"};
    public static final String[] TALENTS_DEVILS_BARTER = {"Devil's Barter", "Increase sell price of items by 20% per point"};
    public static final String[] TALENTS_RIFLE_EXPERTISE = {"Rifle Expertise", "Increases your damage with Rifles by 15% per point"};
    public static final String[] TALENTS_MINIGUN_EXPERTISE = {"Minigun Expertise", "Increases your damage with Minigun by 15% per point"};
    public static final String[] TALENTS_COMPOUND_1080 = {"Compound 1080", "Increases your Heat and Poison damage by 70%"};
    public static final String[] TALENTS_FRAGTURE = {"Fracture", "Increases your Frost and Impact damage by 70%"};
    public static final String[] TALENTS_SNIPER_EXPERTISE = {"Sniper Expertise", "Increases your damage with Sniper by 15% per point"};
    public static final String[] TALENTS_PERFECTING_THE_COMPOUND = {"Perfecting the Compound", "Increases your Heat and Poison damage by 10% per point"};
    public static final String[] TALENTS_AUTO_CORRECTING_AIM = {"Auto-correcting-aim", "Double your critical hit chance"};
    public static final String[] TALENTS_PERFECTING_THE_BLAST = {"Perfecting the Blast", "Increases your Frost and Impact damage by 10% per point"};
    public static final String[] TALENTS_ALMOST_PERFECT = {"Almost Perfect", "Increases your critical hit damage by 5% per point"};








    public static Map<String,Talent> generateTalentList(PlayerImpl player){
        HashMap<String,Talent> talents = new HashMap<>();

        // ## lvl 0 ##
        talents.put(GameConstants.TALENTS_PRECISION[0],
                new Talent(player, new CritTalent(player,1),5,GameConstants.TALENTS_PRECISION,0,null));

        talents.put(GameConstants.TALENTS_ASSAULT_TRAINING[0],
                new Talent(player, new RifleDamageTalent(player,0.10), 5,GameConstants.TALENTS_ASSAULT_TRAINING,0,null));

        talents.put(GameConstants.TALENTS_DISCIPLINED[0],
                new Talent(player, new SniperDamageTalent(player,0.05), 5,GameConstants.TALENTS_DISCIPLINED,0,null));

        talents.put(GameConstants.TALENTS_HEAVY[0],
                new Talent(player, new MinigunDamageTalent(player,0.01), 5,GameConstants.TALENTS_HEAVY,0,null));

        // ## lvl 5 ##
        talents.put(GameConstants.TALENTS_SLEIGHT_OF_HAND[0],
                new Talent(player, new AttackSpeedTalent(player,0.01), 5,GameConstants.TALENTS_SLEIGHT_OF_HAND,5,null));

        talents.put(GameConstants.TALENTS_ENLIGHTENMENT[0],
                new Talent(player, new ExpGainedTalent(player,0.01), 5,GameConstants.TALENTS_ENLIGHTENMENT,5,null));

        // ## lvl 10 ##
        talents.put(GameConstants.TALENTS_IMPACT_BULLET[0],
                new Talent(player, new CritDamageTalent(player,0.05), 2,GameConstants.TALENTS_IMPACT_BULLET,10,GameConstants.TALENTS_PRECISION[0]));

        // ## lvl 15 ##
        talents.put(GameConstants.TALENTS_RAPID_FIRE[0],
                new Talent(player, new AttackSpeedTalent(player,0.05), 4,GameConstants.TALENTS_RAPID_FIRE,15,GameConstants.TALENTS_SLEIGHT_OF_HAND[0]));

        talents.put(GameConstants.TALENTS_LOOTER[0],
                new Talent(player, new BuksGainedTalent(player,0.05), 5,GameConstants.TALENTS_LOOTER,15,null));

        // ## lvl 20 ##
        talents.put(GameConstants.TALENTS_STEADY_NERVES[0],
                new Talent(player, new CritTalent(player,5), 4,GameConstants.TALENTS_STEADY_NERVES,20,null));

        talents.put(GameConstants.TALENTS_EXPOSE_WEAKNESS[0],
                new Talent(player, new CritTalent(player,0), 1,GameConstants.TALENTS_EXPOSE_WEAKNESS,20,GameConstants.TALENTS_DISCIPLINED[0]));

        // ## lvl 25 ##
        talents.put(GameConstants.TALENTS_BLACK_FROST[0],
                new Talent(player, new FrostDamageTalent(player,0.25), 1,GameConstants.TALENTS_BLACK_FROST,25,null));

        talents.put(GameConstants.TALENTS_HELLFIRE[0],
                new Talent(player, new HeatDamageTalent(player,0.25), 1,GameConstants.TALENTS_HELLFIRE,25,null));

        talents.put(GameConstants.TALENTS_MALIGNANT_FORCE[0],
                new Talent(player, new PoisonDamageTalent(player,0.25), 1,GameConstants.TALENTS_MALIGNANT_FORCE,25,null));

        talents.put(GameConstants.TALENTS_RUPTURE[0],
                new Talent(player, new ImpactDamageTalent(player,0.25), 1,GameConstants.TALENTS_RUPTURE,25,null));

        // ## lvl 30 ##
        talents.put(GameConstants.TALENTS_RIME_ROUNDS[0],
                new Talent(player, new FrostDamageTalent(player,0.01), 10,GameConstants.TALENTS_RIME_ROUNDS,30,GameConstants.TALENTS_BLACK_FROST[0]));

        talents.put(GameConstants.TALENTS_WILDFIRE_ROUNDS[0],
                new Talent(player, new HeatDamageTalent(player,0.01), 10,GameConstants.TALENTS_WILDFIRE_ROUNDS,30,GameConstants.TALENTS_HELLFIRE[0]));

        talents.put(GameConstants.TALENTS_INFECTED_ROUNDS[0],
                new Talent(player, new PoisonDamageTalent(player,0.01), 10,GameConstants.TALENTS_INFECTED_ROUNDS,30,GameConstants.TALENTS_MALIGNANT_FORCE[0]));

        talents.put(GameConstants.TALENTS_PENETRATING_ROUNDS[0],
                new Talent(player, new ImpactDamageTalent(player,0.01), 10,GameConstants.TALENTS_PENETRATING_ROUNDS,30,GameConstants.TALENTS_RUPTURE[0]));

        // ## lvl 35 ##
        talents.put(GameConstants.TALENTS_HUSTLER[0],
                new Talent(player, new BuksGainedTalent(player,0.05), 5,GameConstants.TALENTS_HUSTLER,35,null));

        talents.put(GameConstants.TALENTS_COOLHEADED[0],
                new Talent(player, new CritTalent(player,5), 5,GameConstants.TALENTS_COOLHEADED,35,null));

        talents.put(GameConstants.TALENTS_HARDEN[0],
                new Talent(player, new FlatDamageTalent(player,0.01), 5,GameConstants.TALENTS_HARDEN,35,null));

        // ## lvl 40 ##
        talents.put(GameConstants.TALENTS_TRIGGER_FINGERS[0],
                new Talent(player, new AttackSpeedTalent(player,0.03), 3,GameConstants.TALENTS_TRIGGER_FINGERS,40,null));

        // ## lvl 45 ##
        talents.put(GameConstants.TALENTS_BLOODMONEY[0], //TODO DOUBLE AMOUNT
                new Talent(player, new BuksGainedTalent(player,1.0), 1,GameConstants.TALENTS_BLOODMONEY,45,GameConstants.TALENTS_HUSTLER[0]));

        talents.put(GameConstants.TALENTS_DEADLY_PRECESSION[0],
                new Talent(player, new CritDamageTalent(player,0.1), 5,GameConstants.TALENTS_DEADLY_PRECESSION,45,GameConstants.TALENTS_COOLHEADED[0]));

        // ## lvl 50 ##
        talents.put(GameConstants.TALENTS_DEVILS_BARTER[0], //TODO SELL STUFF
                new Talent(player, new ImpactDamageTalent(player,0.01), 1,GameConstants.TALENTS_DEVILS_BARTER,50,GameConstants.TALENTS_BLOODMONEY[0]));

        talents.put(GameConstants.TALENTS_RIFLE_EXPERTISE[0],
                new Talent(player, new RifleDamageTalent(player,0.15), 5,GameConstants.TALENTS_RIFLE_EXPERTISE,50,null));

        talents.put(GameConstants.TALENTS_MINIGUN_EXPERTISE[0],
                new Talent(player, new MinigunDamageTalent(player,0.15), 5,GameConstants.TALENTS_MINIGUN_EXPERTISE,50,null));

        // ## lvl 55 ##
        talents.put(GameConstants.TALENTS_COMPOUND_1080[0],
                new Talent(player, new GasDamageTalent(player,0.7), 1,GameConstants.TALENTS_COMPOUND_1080,55,null));

        talents.put(GameConstants.TALENTS_FRAGTURE[0],
                new Talent(player, new ShatterDamageTalent(player,0.7), 1,GameConstants.TALENTS_FRAGTURE,55,null));

        // ## lvl 60 ##
        talents.put(GameConstants.TALENTS_SNIPER_EXPERTISE[0],
                new Talent(player, new SniperDamageTalent(player,0.15), 5,GameConstants.TALENTS_SNIPER_EXPERTISE,55,null));

        // ## lvl 65 ##
        talents.put(GameConstants.TALENTS_PERFECTING_THE_COMPOUND[0],
                new Talent(player, new GasDamageTalent(player,0.1), 0,GameConstants.TALENTS_PERFECTING_THE_COMPOUND,60,GameConstants.TALENTS_COMPOUND_1080[0]));

        talents.put(GameConstants.TALENTS_AUTO_CORRECTING_AIM[0],
                new Talent(player, new DoubleCritTalent(player), 1,GameConstants.TALENTS_AUTO_CORRECTING_AIM,60,GameConstants.TALENTS_DEADLY_PRECESSION[0]));

        talents.put(GameConstants.TALENTS_PERFECTING_THE_BLAST[0],
                new Talent(player, new ShatterDamageTalent(player,0.1), 0,GameConstants.TALENTS_PERFECTING_THE_BLAST,60,GameConstants.TALENTS_FRAGTURE[0]));

        // ## lvl 70 ##
        talents.put(GameConstants.TALENTS_ALMOST_PERFECT[0],
                new Talent(player, new CritDamageTalent(player,0.05), 0,GameConstants.TALENTS_ALMOST_PERFECT,60,GameConstants.TALENTS_AUTO_CORRECTING_AIM[0]));

        // ## lvl 75 ##

        return talents;
    }

}
