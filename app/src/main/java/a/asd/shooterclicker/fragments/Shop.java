package a.asd.shooterclicker.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import a.asd.shooterclicker.R;
import a.asd.shooterclicker.adapters.ItemShopList;
import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.patterns.Game;

public class Shop extends Fragment {

    public Shop() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);


        ExpandableListView expandableListView = view.findViewById(R.id.weaponShopList);
        ItemShopList adapter = new ItemShopList(getContext(), Game.getInstance().getPlayer());
        expandableListView.setAdapter(adapter);


        return view;
    }

}
