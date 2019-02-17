package a.asd.shooterclicker.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import a.asd.shooterclicker.R;
import a.asd.shooterclicker.activities.MainActivity;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Weapon;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.WeaponImpl;

public class ItemShopList extends BaseExpandableListAdapter {


    private LayoutInflater inflater;
    private AlertDialog alertDialog;
    private int[] priceConstants = { GameConstants.COMMON_BASE_PRICE,
            GameConstants.UNCOMMON_BASE_PRICE,
            GameConstants.RARE_BASE_PRICE};

    private int[] damageConstants = { GameConstants.COMMON_WEAPON_BASE_DAMAGE,
            GameConstants.UNCOMMON_WEAPON_BASE_DAMAGE,
            GameConstants.RARE_WEAPON_BASE_DAMAGE,};

    private String[] rarity = { GameConstants.RARITY_COMMON,
            GameConstants.RARITY_UNCOMMON,
            GameConstants.RARITY_RARE};

    String[] groupNames; //Names of the Dropdowns
    String[][] childNames; //Content of the Dropdowns

    Context context; //The context of the view.

    public ItemShopList(Context context, PlayerImpl player){
        this.context = context;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //set inflater


        int numberOfItems = 0;
        //initialize the groups

        for(int i = 0; i<=player.getLevel();i += 5){
            numberOfItems++;
        }
        groupNames = new String[numberOfItems];
        childNames = new String[numberOfItems][3];

        for(int i = 0; i<numberOfItems;i++){
            groupNames[i] = i*5+"";
        }
    }

    /**
     * set group count
     * @return group length
     */
    @Override
    public int getGroupCount() {
        return groupNames.length;
    }

    /**
     * returns the children count
     * @param groupPosition the current postion in the array
     * @return the child count
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return childNames[groupPosition].length;
    }

    /**
     * get the current group
     * @param groupPosition the current postion
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return groupNames[groupPosition];
    }

    /**
     * get the current child
     * @param groupPosition postionten
     * @param childPosition the current child position
     * @return a politician
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childNames[groupPosition][childPosition];
    }

    /**
     * get the group id
     * @param groupPosition current position
     * @return the group id
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * get the child id
     * @param groupPosition group position
     * @param childPosition child position
     * @return the child id
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Not used
     * @return always returns false
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Generates a groupView for a giving position. By returning a View of a new progress bar.
     *
     * @return A progress bar with the appropriate strength as Progress.
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if(view == null) //Check if null
        {
            view=inflater.inflate(R.layout.shop_items, null);
        }

        TextView levelLabel = view.findViewById(R.id.shopLevelLabel);
        TextView itemLabel = view.findViewById(R.id.itemLevelLabelShop);

        levelLabel.setText(groupNames[groupPosition]);
        if(groupNames[groupPosition].equals("0")){
            levelLabel.setText("");
            itemLabel.setText("Custom");
        }

        return view;
    }

    /**
     * method generates the textview that encapsulated the appropriate text to a given strength
     *
     * @return A textview with the politicians comment on his/her strength
     */
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if(view == null) //Check if null
        {
            view=inflater.inflate(R.layout.expandend_items, null);
        }

        TextView price = view.findViewById(R.id.weaponPrice);
        TextView damage = view.findViewById(R.id.shopWeaponDamage);
        ConstraintLayout cl = view.findViewById(R.id.shopBagground);


        final int itemLevel = groupPosition * 5;

        int lowend = WeaponImpl.lowEnd(damageConstants[childPosition],itemLevel);
        int highend = WeaponImpl.highEnd(damageConstants[childPosition],itemLevel);

        final int weaponPrice = priceConstants[childPosition] * itemLevel;


        if(childPosition == 0){
            cl.setBackgroundResource(R.drawable.rarity_common);
        } else if(childPosition == 1){
            cl.setBackgroundResource(R.drawable.rarity_uncommon);
        } else {
            cl.setBackgroundResource(R.drawable.rarity_rare);
        }


        price.setText(weaponPrice+"");
        damage.setText("["+lowend+"-"+highend+"]");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerImpl player = Game.getInstance().getPlayer();
                if(Game.getInstance().getPlayer().getBuks() >= weaponPrice){
                    WeaponImpl weapon = new WeaponImpl();
                    weapon.generateWeapon(rarity[childPosition],itemLevel);
                    player.addWeapon(weapon);
                    player.removeBuks(weaponPrice);
                    ((MainActivity)context).updateView();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                    View child = a.asd.shooterclicker.fragments.Weapon.setUIWeapon(weapon,context,inflater.inflate(R.layout.weapon, null));

                    child.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });

                    alertDialogBuilder.setView(child);

                    alertDialog = alertDialogBuilder.create();

                    alertDialog.show();
                }
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


}

