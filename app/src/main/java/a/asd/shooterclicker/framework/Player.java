package a.asd.shooterclicker.framework;

import java.util.Map;

import a.asd.shooterclicker.standard.Talent;

public interface Player {

    long getExperience();

    int getLevel();

    long getBuks();

    long getExpReq();

    Weapon getCurrentWeapon();

    Map<String,Talent> getTalents();

}
