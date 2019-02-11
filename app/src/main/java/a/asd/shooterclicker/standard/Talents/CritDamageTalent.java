package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.standard.PlayerImpl;

public class CritDamageTalent implements TalentStrategy {

    private final PlayerImpl player;
    private final double amount;

    public CritDamageTalent(PlayerImpl player, double amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().increaseCritModifier(amount);
    }
}
