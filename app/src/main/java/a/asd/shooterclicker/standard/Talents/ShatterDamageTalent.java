package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;

public class ShatterDamageTalent implements TalentStrategy {

    private final PlayerImpl player;
    private final double amount;

    public ShatterDamageTalent(PlayerImpl player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().setPercentFrost(Generator.format( player.changeStats().getPercentFrost() + amount ));
        player.changeStats().setPercentInpact(Generator.format( player.changeStats().getPercentInpact() + amount ));

    }
}
