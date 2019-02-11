package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.PlayerImpl;

public class DoubleCritTalent implements TalentStrategy {
    private PlayerImpl player;

    public DoubleCritTalent(PlayerImpl player) {
        this.player = player;

    }

    @Override
    public void effect() {
        player.changeStats().setCritMultiplyer(2.0);
    }
}
