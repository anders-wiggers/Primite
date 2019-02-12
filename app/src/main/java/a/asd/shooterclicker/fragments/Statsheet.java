package a.asd.shooterclicker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import a.asd.shooterclicker.R;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.PlayerStats;
import a.asd.shooterclicker.standard.WeaponImpl;

public class Statsheet extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats, container, false);

        WeaponImpl weapon = Game.getInstance().getMoveWeapon();

        return setUIWeapon(weapon,getContext(),view);
    }

    private View setUIWeapon(WeaponImpl weapon, final Context context, View weaponView){
        View view = weaponView;

        ConstraintLayout constraintLayout = view.findViewById(R.id.weaponRairty);

        if(weapon.getRarity().equals(GameConstants.RARITY_UNCOMMON)){
            constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.rarity_uncommon));
        } else if (weapon.getRarity().equals(GameConstants.RARITY_RARE)){
            constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.rarity_rare));
        }else if (weapon.getRarity().equals(GameConstants.RARITY_EPIC)){
            constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.rarity_epic));
        }else if (weapon.getRarity().equals(GameConstants.RARITY_LEGENDARY)){
            constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.rarity_legendary));
        } else {
            constraintLayout.setBackground(ContextCompat.getDrawable(context,R.drawable.rarity_common));
        }

        TextView damage = view.findViewById(R.id.statDamage);
        TextView speed = view.findViewById(R.id.statAttSpeed);
        TextView dps = view.findViewById(R.id.statDPS);
        TextView crit = view.findViewById(R.id.statCritChance);
        TextView critMulti = view.findViewById(R.id.statCritMultiplier);

        PlayerStats playerStats = Game.getInstance().getPlayer().changeStats();

        crit.setText(playerStats.getCritChance()+"%" );

        double critmodifyer = Game.getInstance().getPlayer().changeStats().getCritModifier();

        critmodifyer = critmodifyer * 100;
        int inted = ((int) critmodifyer) + 1;
        String critModifyerString = inted+"%";

        critMulti.setText(critModifyerString);

        speed.setText(weapon.getAttackSpeed()+"");

        ImageView gun = view.findViewById(R.id.weaponModel);

        if(weapon.getModel().equals(GameConstants.RIFLE)) gun.setImageResource(R.drawable.rifle_model);
        if(weapon.getModel().equals(GameConstants.HEAVY_SNIPER)) gun.setImageResource(R.drawable.sniper_model);
        if(weapon.getModel().equals(GameConstants.MINI_GUN)) gun.setImageResource(R.drawable.mini_model);

        damage.setText(weapon.getBaseDamage()+"");

        int averageDps = 0;

        double baseWithModifiers = weapon.getBaseDamage() +
                weapon.getBaseDamage() * playerStats.getPercentFrost() +
                weapon.getBaseDamage() * playerStats.getPercentHeat() +
                weapon.getBaseDamage() * playerStats.getPercentPoison() +
                weapon.getBaseDamage() * playerStats.getPercentInpact();

        if(weapon.getModel().equals(GameConstants.RIFLE)){
            baseWithModifiers = baseWithModifiers * playerStats.getPercentDamageToRifle();
        } else if( weapon.getModel().equals(GameConstants.HEAVY_SNIPER)){
            baseWithModifiers = baseWithModifiers * playerStats.getPercentDamageToSniper();
        } else {
            baseWithModifiers = baseWithModifiers * playerStats.getPercentDamageToMini();
        }

        baseWithModifiers = baseWithModifiers * playerStats.getPercentFlatDamageBoost();

        double critChance = playerStats.getCritChance()/100.0;

        averageDps = (int) ((critChance * baseWithModifiers * playerStats.getCritModifier() +
                (1-critChance) * baseWithModifiers) * weapon.getAttackSpeed());


        dps.setText(averageDps+"");

        return view;
    }
}
