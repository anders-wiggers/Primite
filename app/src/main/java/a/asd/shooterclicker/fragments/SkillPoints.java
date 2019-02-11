package a.asd.shooterclicker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import a.asd.shooterclicker.R;
import a.asd.shooterclicker.adapters.ItemShopList;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.framework.Observer;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.Talent;
import a.asd.shooterclicker.standard.WeaponImpl;

public class SkillPoints extends Fragment implements Observer {
    private AlertDialog alertDialog;
    private View view;
    private LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.skill_point_menu, container, false);
        this.inflater = inflater;


        Game.getInstance().getPlayer().addObserver(this);
        generateButtons();
        //redrawButtons();

        return view;
    }

    public static View setUITalent(final Talent talent, final PlayerImpl player, View talentDialog, final Fragment fragment){
        View view = talentDialog;

        TextView talentTitle = view.findViewById(R.id.talent_title);
        TextView talentText = view.findViewById(R.id.talent_text);
        final TextView talentProgression = view.findViewById(R.id.talent_progression);

        talentTitle.setText(talent.getTitle());
        talentText.setText(talent.getBody());
        if(talent.getMaxPoint() == 9999) talentProgression.setText(talent.getPoint()+"/*");
        else talentProgression.setText(talent.getPoint()+"/"+talent.getMaxPoint());


        Button add = view.findViewById(R.id.talent_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.putTalentPoint(talent.getTitle())){
                    if(talent.getMaxPoint() == 9999) talentProgression.setText(talent.getPoint()+"/*");
                    else talentProgression.setText(talent.getPoint()+"/"+talent.getMaxPoint());
                    ((SkillPoints)fragment).generateButtons();

                }
            }
        });

        return view;
    }

    public void generateTalentDialog(String ctalent, LayoutInflater inflater){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        PlayerImpl player = Game.getInstance().getPlayer();

        Talent talent = player.getTalents().get(ctalent);

        View child = setUITalent(talent,player,inflater.inflate(R.layout.talent_dialog, null),this);

        alertDialogBuilder.setView(child);

        alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private void handleButton(View view, int id, final String[] talentConst,final LayoutInflater inflater){
        Button precision = view.findViewById(id);

        Talent t = Game.getInstance().getPlayer().getTalents().get(talentConst[0]);

        if(t.getMaxPoint()==9999) precision.setText(t.getPoint()+"/ *");
        else precision.setText(t.getPoint()+"/"+t.getMaxPoint());


        precision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateTalentDialog(talentConst[0],inflater);
            }
        });
    }

    public void redrawButtons(){
        handleButton(view,R.id.critChance,GameConstants.TALENTS_PRECISION,inflater);

        handleButton(view,R.id.critDamage,GameConstants.TALENTS_IMPACT_BULLET,inflater);

        handleButton(view,R.id.rifleDamage,GameConstants.TALENTS_ASSAULT_TRAINING,inflater);

        handleButton(view,R.id.snipeDamage,GameConstants.TALENTS_DISCIPLINED,inflater);

        handleButton(view,R.id.miniDamage,GameConstants.TALENTS_HEAVY,inflater);
    }

    public void generateButtons(){
        final String[][] talents = GameConstants.talentsButtonMap();
        PlayerImpl player = Game.getInstance().getPlayer();


        int pixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, getResources().getDisplayMetrics());


        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                pixel,
                pixel,
                1.0f
        );

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setPadding(30,30,30,30);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        ((ScrollView) view.findViewById(R.id.talentScroll)).removeAllViews();
        ((ScrollView) view.findViewById(R.id.talentScroll)).addView(linearLayout);

        for( int i = 0 ; i < 15 ; i++ ){

            LinearLayout levelBar = new LinearLayout(getContext());
            levelBar.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(10,10,10,20);

            levelBar.setLayoutParams(layoutParams);

            linearLayout.addView(levelBar);



            for (int j = 0 ; j<4 ; j++){
                final String cTalent = talents[i][j];
                Button button = new Button(getContext());

                button.setLayoutParams(param);
                button.setHeight(pixel);
                button.setWidth(pixel);

                int buttonId = button.generateViewId();
                button.setId(buttonId);

                ConstraintLayout constraintLayout = new ConstraintLayout(getContext());

                if(null == cTalent) {
                    button.setVisibility(View.INVISIBLE);
                    constraintLayout.addView(button);
                }
                else if("filler".equals(cTalent)){
                    ConstraintLayout cons = new ConstraintLayout(getContext());
                    cons.setId(buttonId);
                    LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(
                            225, 225);
                    cons.setLayoutParams(viewParams);



                    View view = new View(getContext());
                    LinearLayout.LayoutParams viewParams1 = new LinearLayout.LayoutParams(
                            20, 225);
                    view.setLayoutParams(viewParams1);

                    view.setBackgroundResource(chooseColorLinkedTalents(player,talents,i,j));

                    int viewId = view.generateViewId();
                    view.setId(viewId);
                    cons.addView(view);
                    constraintLayout.addView(cons);

                    ConstraintSet cs = new ConstraintSet();
                    cs.clone(cons);

                    cs.connect(viewId, ConstraintSet.RIGHT, buttonId, ConstraintSet.RIGHT,0);
                    cs.connect(viewId, ConstraintSet.LEFT, buttonId, ConstraintSet.LEFT,0);
                    cs.connect(viewId, ConstraintSet.TOP, buttonId, ConstraintSet.TOP,0);
                    cs.connect(viewId, ConstraintSet.BOTTOM, buttonId, ConstraintSet.BOTTOM,0);

                    cs.applyTo(cons);

                }
                else {
                    Talent talent = player.getTalents().get(cTalent);

                    if (talent.getMaxPoint() == 9999) button.setText(talent.getPoint() + "/* \n" +cTalent);
                    else button.setText(talent.getPoint() + "/" + talent.getMaxPoint()+"\n"+cTalent);



                    if(talent.getPoint()>0 && !talent.isComplete()){
                        button.setBackgroundResource(R.drawable.rarity_epic);
                    }
                    else if(talent.isComplete()){
                        button.setBackgroundResource(R.drawable.rarity_legendary);
                    }
                    else if(talent.isAvailable()){
                        button.setBackgroundResource(R.drawable.rarity_uncommon);
                    }
                    else{
                        button.setBackgroundResource(R.drawable.rarity_common);
                    }



                    button.setGravity(1);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            generateTalentDialog(cTalent, inflater);
                        }
                    });
                    constraintLayout.addView(button);
                }




                int constainLayoutId = constraintLayout.generateViewId();
                constraintLayout.setId(constainLayoutId);


                LinearLayout.LayoutParams sparam = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f
                );
                constraintLayout.setLayoutParams(sparam);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);

                constraintSet.connect(buttonId, ConstraintSet.RIGHT, constainLayoutId, ConstraintSet.RIGHT,0);
                constraintSet.connect(buttonId, ConstraintSet.LEFT, constainLayoutId, ConstraintSet.LEFT,0);
                constraintSet.connect(buttonId, ConstraintSet.TOP, constainLayoutId, ConstraintSet.TOP,0);
                constraintSet.connect(buttonId, ConstraintSet.BOTTOM, constainLayoutId, ConstraintSet.BOTTOM,0);

                constraintSet.applyTo(constraintLayout);

                levelBar.addView(constraintLayout);
            }
        }
    }


    private int chooseColorLinkedTalents(PlayerImpl player, String[][] talents, int i, int j){
        int resource = 0;

        if(checkIfComplete(player,talents,i,j)){
            return R.drawable.rarity_legendary;
        }

        if(talents[i-1][j].equals("filler")) {
            resource = chooseColorLinkedTalents(player,talents,i-1,j);
        }
        else if(player.getTalents().get(talents[i-1][j]).isComplete() && player.getSkillsPoints()>0){
            resource = R.drawable.rarity_uncommon;
        }
        else {
            resource = R.drawable.rarity_common;
        }

        return resource;
    }

    private boolean checkIfComplete(PlayerImpl player, String[][] talents, int i, int j){
        boolean completed = false;

        if(talents[i+1][j].equals("filler")) {
            completed = checkIfComplete(player,talents,i+1,j);
        }
        else if(player.getTalents().get(talents[i+1][j]).isComplete()){
            completed =true;
        }
        return completed;
    }

    @Override
    public void update(boolean update) {
        generateButtons();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Game.getInstance().getPlayer().removeObserver(this);
    }
}
