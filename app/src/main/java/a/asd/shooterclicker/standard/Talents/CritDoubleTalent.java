package a.asd.shooterclicker.standard.Talents;

import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.standard.PlayerImpl;

public class CritDoubleTalent implements TalentStrategy {

    private final PlayerImpl player;

    public CritDoubleTalent(PlayerImpl player) {
        this.player = player;
    }

    @Override
    public void effect() {
        player.changeStats().setCritChance(player.changeStats().getCritChance()*2);
    }
}
