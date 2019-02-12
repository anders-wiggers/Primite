package a.asd.shooterclicker.standard.Strategies.EnemyStrategies;

import java.util.Map;
import java.util.TreeMap;

import a.asd.shooterclicker.framework.EnemyStrategies.LootStrategy;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.framework.Weapon;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.WeaponImpl;

public class BossLoot implements LootStrategy {
    PlayerImpl player;

    public BossLoot(){
        player = Game.getInstance().getPlayer();
    }

    @Override
    public Weapon getLoot() {
        return generateLoot();
    }

    private Weapon generateLoot() {
        int range = 0;
        TreeMap<Integer, WeaponImpl> lootTable = new TreeMap<>();

        for(Map.Entry<String, Integer> entry : GameConstants.LOOT_TABLE.entrySet()){
            range += entry.getValue();
            WeaponImpl weapon = new WeaponImpl();
            weapon.generateWeapon(entry.getKey(),player.getLevel());
            lootTable.put(range,weapon);
        }

        int gen = Generator.generateRandom(1,range);

        return lootTable.ceilingEntry(gen).getValue();
    }
}
