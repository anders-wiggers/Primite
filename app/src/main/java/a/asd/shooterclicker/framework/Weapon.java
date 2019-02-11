package a.asd.shooterclicker.framework;

public interface Weapon {

    long getBaseDamage();

    double getAttackSpeed();

    String getRarity();

    String getName();

    String getModel();

    void generateWeapon(String rarity, int itemLevel);

}
