package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;

public class BuksGainedTalent implements TalentStrategy {
    private PlayerImpl player;
    private double amount;

    public BuksGainedTalent(PlayerImpl player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().setBuksGain(Generator.format( player.changeStats().getBuksGain() + amount ));
    }
}
