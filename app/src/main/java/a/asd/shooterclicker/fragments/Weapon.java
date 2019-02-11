package a.asd.shooterclicker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import a.asd.shooterclicker.R;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.standard.WeaponImpl;

public class Weapon extends Fragment {



    public Weapon() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weapon, container, false);

        WeaponImpl weapon = Game.getInstance().getMoveWeapon();

        /*
        ConstraintLayout constraintLayout = view.findViewById(R.id.weaponViewLayout);

        if(weapon.getRarity().equals(GameConstants.RARITY_UNCOMMON)){
            constraintLayout.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.rarity_uncommon));
        } else if (weapon.getRarity().equals(GameConstants.RARITY_RARE)){
            constraintLayout.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.rarity_rare));
        }else if (weapon.getRarity().equals(GameConstants.RARITY_EPIC)){
            constraintLayout.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.rarity_epic));
        }else if (weapon.getRarity().equals(GameConstants.RARITY_LEGENDARY)){
            constraintLayout.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.rarity_legendary));
        } else {
            constraintLayout.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.rarity_common));
        }

        TextView damage = view.findViewById(R.id.weaponViewDamage);
        TextView speed = view.findViewById(R.id.weaponViewSpeed);
        TextView dps = view.findViewById(R.id.weaponViewDPS);
        TextView lv = view.findViewById(R.id.weaponViewLevel);
        TextView worth = view.findViewById(R.id.weaponViewWorth);
        TextView name = view.findViewById(R.id.weaponViewName);

        worth.setText(weapon.getWorth()+"");
        name.setText(weapon.getName());
        speed.setText(weapon.getAttackSpeed()+"");
        damage.setText(weapon.getBaseDamage()+"");
        dps.setText(weapon.getBaseDamage()*weapon.getAttackSpeed()+"");

        */
        return setUIWeapon(weapon,getContext(),view);
    }

    public static View setUIWeapon(WeaponImpl weapon, final Context context, View weaponView){
        View view = weaponView;

        ConstraintLayout constraintLayout = view.findViewById(R.id.weaponViewLayout);

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

        TextView damage = view.findViewById(R.id.weaponViewDamage);
        TextView speed = view.findViewById(R.id.weaponViewSpeed);
        TextView dps = view.findViewById(R.id.weaponViewDPS);
        TextView lv = view.findViewById(R.id.weaponViewLevel);
        TextView worth = view.findViewById(R.id.weaponViewWorth);
        TextView name = view.findViewById(R.id.weaponViewName);

        lv.setText(weapon.getItemLevel()+"");
        worth.setText(weapon.getWorth()+"");
        name.setText(weapon.getName());
        speed.setText(weapon.getAttackSpeed()+"");
        damage.setText(weapon.getBaseDamage()+"");
        dps.setText(weapon.getBaseDamage()*weapon.getAttackSpeed()+"");

        return view;
    }
}
