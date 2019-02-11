package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;

public class ExpGainedTalent implements TalentStrategy {
    private PlayerImpl player;
    private double amount;

    public ExpGainedTalent(PlayerImpl player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().setExpGain(Generator.format( player.changeStats().getExpGain() + amount ));
    }
}
