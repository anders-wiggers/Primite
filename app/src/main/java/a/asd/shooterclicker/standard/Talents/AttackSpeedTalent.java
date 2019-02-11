package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;

public class AttackSpeedTalent implements TalentStrategy {
    private PlayerImpl player;
    private double amount;

    public AttackSpeedTalent(PlayerImpl player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().setPercentAttackSpeedBoost(Generator.format( player.changeStats().getPercentAttackSpeedBoost() + amount ));
    }
}
