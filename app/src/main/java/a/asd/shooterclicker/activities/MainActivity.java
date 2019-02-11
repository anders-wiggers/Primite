package a.asd.shooterclicker.activities;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import a.asd.shooterclicker.R;
import a.asd.shooterclicker.fragments.Shop;
import a.asd.shooterclicker.fragments.SkillPoints;
import a.asd.shooterclicker.fragments.WeaponList;
import a.asd.shooterclicker.framework.Enemy;
import a.asd.shooterclicker.framework.GameConstants;
import a.asd.shooterclicker.framework.HorizontalProgressView;
import a.asd.shooterclicker.framework.Observer;
import a.asd.shooterclicker.framework.Player;
import a.asd.shooterclicker.framework.Weapon;
import a.asd.shooterclicker.patterns.Game;
import a.asd.shooterclicker.patterns.Generator;
import a.asd.shooterclicker.standard.Damage.Damage;
import a.asd.shooterclicker.standard.EnemyImpl;
import a.asd.shooterclicker.standard.PlayerImpl;
import a.asd.shooterclicker.standard.Strategies.EnemyStrategies.StandardHealth;
import a.asd.shooterclicker.standard.WeaponImpl;

public class MainActivity extends AppCompatActivity implements Observer {
    private WeaponImpl currentWeapon;
    private EnemyImpl enemy;
    private PlayerImpl player;

    private HorizontalProgressView enemyHealth;
    private TextView buks;
    private TextView currentLevel;
    private ProgressBar expericenBar;


    private TextView damage;
    private TextView speed;
    private TextView dps;
    private Button shopButton;
    private Button openSkill;

    private Timer timer = new Timer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Find Views
        openSkill = findViewById(R.id.openSkill);
        enemyHealth = findViewById(R.id.progressBar);
        buks = findViewById(R.id.buks);
        currentLevel = findViewById(R.id.currentLevel);
        expericenBar = findViewById(R.id.expBar);
        shopButton = findViewById(R.id.openShop);

        player = Game.getInstance().getPlayer();
        player.addObserver(this);
        currentWeapon = (WeaponImpl) Game.getInstance().getPlayer().getCurrentWeapon();

        enemy = new EnemyImpl(player,"bob",new StandardHealth(player));

        Log.e("Weapon", player.getCurrentWeapon().toString());
        Log.e("Enemy", enemy.toString());

        //timer(); //TODO REACTIVATE
        updateView();
    }

    public void damageEnemy(View view){
        damageEnemy();
    }

    public void timer(){
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        damageEnemy();
                    }
                });
            }
        };



        double interval = 1000 / currentWeapon.getAttackSpeed();
        long intevalPeriod = (long) interval;
        long delay = 0;


        // schedules the task to be run in an interval
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);
    }


    private void damageEnemy(){
        Damage damage = player.dealDamage();
        if(enemy.damageEnemy(damage,this)){
            player.addBuks(enemy.getWorth());
            player.addExperience(enemy.getExperience());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateView();
                }
            });
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            // Actions to do after 1 animation
                            enemy = new EnemyImpl(player,"bob", new StandardHealth(player));

                            updateView();
                        }
                    }, 50);
                }
            });


        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateView();
                }
            });
        }
    }

    public void updateView(Boolean... reset){
        //enemyHealth.setProgress(enemy.getHealthPercent());
        enemyHealth.animateView((int)enemyHealth.getProgress(),enemy.getHealthPercent());
        ObjectAnimator.ofInt(expericenBar, "progress", player.expPercentage()).start();

        buks.setText(player.getBuks()+"");
        currentLevel.setText(player.getLevel()+"");


        if(!currentWeapon.getName().equals(Game.getInstance().getPlayer().getCurrentWeapon().getName()) || reset.length != 0 && reset[0]){
            currentWeapon = (WeaponImpl) Game.getInstance().getPlayer().getCurrentWeapon();
            Game.getInstance().setMoveWeapon(currentWeapon);
            loadWeapon(new a.asd.shooterclicker.fragments.Statsheet());

            timer.cancel();
            timer.purge();
            timer = new Timer();

            timer();
        }

        if(player.getSkillsPoints()>0){
            openSkill.setText("Unspent Points:"+player.getSkillsPoints());
        }
        else{
            openSkill.setText("Skill points");
        }

        Log.e("Enemy",enemy.toString());
        Log.e("Player", player.getExperience()+"");
    }

    public void openShop(View view){
        Boolean isThereAFragment = getSupportFragmentManager().findFragmentByTag("shop") == null;
        if(isThereAFragment) {
            removeFragment("wepList");
            replaceFragment(new Shop(),"shop");
        }else{
            removeFragment("shop");
        }
    }


    public void replaceFragment(Fragment fragment,String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, fragment,tag);
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public void removeFragment(String tag){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            onBackPressed();
        }
    }

    public void loadWeapon(Fragment fragment){
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("weapon");
        if(currentFragment != null) getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.weaponContainer, fragment,"weapon");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void printGunNames(View view){
        for(Weapon w : player.getWeapons()){
            Log.e("Weapons names:", w.getName());
        }
    }

    public void openPlayerWeapons(View view){
        Boolean isThereAFragment = getSupportFragmentManager().findFragmentByTag("wepList") == null;
        if(isThereAFragment) {
            removeFragment("shop");
            replaceFragment(new WeaponList(),"wepList");
        }else{
            removeFragment("wepList");
        }
    }

    public void closePlayerWeapons(View view){

    }

    public void openSkillSet(View view){
        Boolean isThereAFragment = getSupportFragmentManager().findFragmentByTag("sp") == null;
        if(isThereAFragment) {
            removeFragment("sp");
            replaceFragment(new SkillPoints(),"sp");
        }else{
            removeFragment("sp");
        }
    }

    public void showDamage(int damage){
        final TextView valueTV = new TextView(this);

        int id = valueTV.generateViewId();
        valueTV.setId(id);

        valueTV.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));

        valueTV.setText(""+damage);

        ((ConstraintLayout) findViewById(R.id.damageContainer)).addView(valueTV);

        ConstraintSet set = new ConstraintSet();
        final ConstraintLayout layout;

        layout = findViewById(R.id.damageContainer);
        set.clone(layout);

        int top = Generator.generateRandom(0,800);
        int mid = Generator.generateRandom(-800,800);


        set.connect(id, ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT, mid);
        set.connect(id, ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, -mid);
        set.connect(id, ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, -top);
        set.connect(id, ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM, top);

        set.applyTo(layout);

        valueTV.animate()
                .alpha(0f)
                .setDuration(200)
                .setStartDelay(300)
                .translationY(-30)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        layout.removeView(valueTV);
                    }
                })
                .start();
    }

    @Override
    public void update(boolean updateWeapon) {
        if(updateWeapon) updateView(true);
        updateView();
    }

}
