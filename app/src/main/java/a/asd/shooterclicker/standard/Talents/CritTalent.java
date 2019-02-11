package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.standard.PlayerImpl;

public class CritTalent implements TalentStrategy {

    private final PlayerImpl player;
    private int amount;

    public CritTalent(PlayerImpl player, int amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void effect() {
        player.changeStats().increaseCritChange(amount);
    }
}
