package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;

public class PoisonDamageTalent implements TalentStrategy {

    private final PlayerImpl player;
    private final double amount;

    public PoisonDamageTalent(PlayerImpl player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().setPercentPoison(Generator.format( player.changeStats().getPercentPoison() + amount ));
    }
}
