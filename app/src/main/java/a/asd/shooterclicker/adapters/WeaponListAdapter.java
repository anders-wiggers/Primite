package a.asd.shooterclicker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import a.asd.shooterclicker.R;
import a.asd.shooterclicker.activities.MainActivity;
import a.asd.shooterclicker.fragments.Weapon;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.standard.WeaponImpl;

public class WeaponListAdapter extends ArrayAdapter<WeaponImpl> {
    private final int resource;

    public WeaponListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<WeaponImpl> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(resource,parent,false);//get the view

        ConstraintLayout constraintLayout = convertView.findViewById(R.id.weaponViewLayout);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.getInstance().getPlayer().setCurrentWeapon(getItem(position));
                ((MainActivity)getContext()).updateView();
            }
        });

        constraintLayout.setClickable(true);

        return Weapon.setUIWeapon(getItem(position),getContext(),convertView);
    }
}
