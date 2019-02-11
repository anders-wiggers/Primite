package a.asd.shooterclicker.standard;

import java.util.ArrayList;
import java.util.List;

import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Strategies.TalentStrategy;
import a.asd.shooterclicker.framework.TalentEffect;
import a.asd.shooterclicker.standard.PlayerImpl;

public class Talent implements TalentEffect {

    private final Player player;
    private int point;
    private int maxPoint;
    private TalentStrategy talentStrategy;

    private String requredTalent;

    private int reqerments;


    //info
    private String title;
    private String body;

    public Talent(Player player, TalentStrategy talentStrategy, int maxPoint, String[] text,
                  int requirement,String requiredTalent) {
        this.player = player;
        this.maxPoint = maxPoint;
        if(maxPoint == 0) this.maxPoint = 9999;
        this.talentStrategy = talentStrategy;
        this.reqerments = requirement;

        requredTalent = requiredTalent;

        this.title = text[0];
        this.body = text[1];
    }

    @Override
    public void effect(){
        for(int i = 0 ; i<point;i++){
            talentStrategy.effect();
        }
    }

    @Override
    public boolean addPoint() {
        if(requredTalent != null){
            List<Talent> talentList = new ArrayList(player.getTalents().values());

            for(Talent t : talentList){
                if(t.getTitle().equals(requredTalent)){
                    if(!t.isComplete()){
                        return false;
                    }
                }
            }

        }

        if(reqerments>((PlayerImpl)player).getSpendPoints()){
            return false;
        }

        if(point>=maxPoint){
            return false;
        }

        point++;
        return true;
    }

    @Override
    public boolean isFull() {
        return point==maxPoint;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPoint() {
        return point;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Boolean isComplete(){
        return point==maxPoint;
    }

    public boolean isAvailable(){
        boolean hasSpendEnough = (((PlayerImpl)player).getSpendPoints() >= reqerments);

        boolean hasPointToSpend = ((PlayerImpl)player).getSkillsPoints() > 0;

        boolean hasLearndReq = true;

        if(null != requredTalent) {
           hasLearndReq = player.getTalents().get(requredTalent).isComplete();
        }

        return hasSpendEnough && hasLearndReq && hasPointToSpend;
    }
}


