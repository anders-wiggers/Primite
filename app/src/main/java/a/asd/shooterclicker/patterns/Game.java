package a.asd.shooterclicker.patterns;

import java.util.ArrayList;

import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Weapon;
import a.asd.shooterclicker.standard.EnemyImpl;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.WeaponImpl;

public class Game {
    private static Game game;

    private PlayerImpl player;
    private WeaponImpl moveWeapon;

    private Game(){
        player = new PlayerImpl();
        player.setLevel(1);
        player.setWeapons(new ArrayList<WeaponImpl>());

        WeaponImpl weapon = new WeaponImpl();
        weapon.generateWeapon(GameConstants.RARITY_LEGENDARY,100);
        player.addWeapon(weapon);
        player.setCurrentWeapon(weapon);

    }

    public static Game getInstance(){
        if(game == null){
            game = new Game();
        }
        return game;
    }


    public PlayerImpl getPlayer() {
        return player;
    }

    public void setMoveWeapon(WeaponImpl weapon){
        moveWeapon = weapon;
    }

    public WeaponImpl getMoveWeapon() {
        return moveWeapon;
    }
}
