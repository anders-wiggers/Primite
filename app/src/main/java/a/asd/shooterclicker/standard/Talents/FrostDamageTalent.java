package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;

public class FrostDamageTalent implements TalentStrategy {

    private final PlayerImpl player;
    private final double amount;

    public FrostDamageTalent(PlayerImpl player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().setPercentFrost(Generator.format( player.changeStats().getPercentFrost() + amount ));
    }
}
