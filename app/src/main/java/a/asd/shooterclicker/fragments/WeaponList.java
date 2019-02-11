package a.asd.shooterclicker.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import a.asd.shooterclicker.R;
import a.asd.shooterclicker.adapters.WeaponListAdapter;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.standard.WeaponImpl;

public class WeaponList extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weapon_list, container, false);

        ArrayList<WeaponImpl> weapons = new ArrayList<>();
        weapons.addAll(Game.getInstance().getPlayer().getWeapons());

        WeaponListAdapter adapter = new WeaponListAdapter(getContext(),R.layout.weapon, weapons);
        ListView weaponList = view.findViewById(R.id.playerWeapons);
        weaponList.setAdapter(adapter);

        return view;
    }
}
