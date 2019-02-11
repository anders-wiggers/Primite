package a.asd.shooterclicker.standard.Talents;

import java.text.DecimalFormat;

import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;

public class RifleDamageTalent implements TalentStrategy {
    private PlayerImpl player;
    private double amount;

    public RifleDamageTalent(PlayerImpl player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().setPercentDamageToRifle(Generator.format( player.changeStats().getPercentDamageToRifle() + amount ));
    }
}
