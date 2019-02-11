package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;

public class GasDamageTalent implements TalentStrategy {

    private final PlayerImpl player;
    private final double amount;

    public GasDamageTalent(PlayerImpl player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().setPercentHeat(Generator.format( player.changeStats().getPercentHeat() + amount ));
        player.changeStats().setPercentPoison(Generator.format( player.changeStats().getPercentPoison() + amount ));

    }
}
