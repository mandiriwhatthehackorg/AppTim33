package men.ngopi.zakwan.jasanesia.Intro;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import men.ngopi.zakwan.jasanesia.ChatActivity;
import men.ngopi.zakwan.jasanesia.Login.LoginActivity;
import men.ngopi.zakwan.jasanesia.MainActivity;
import men.ngopi.zakwan.jasanesia.R;
import men.ngopi.zakwan.jasanesia.Utils.PublicString;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;
    TabLayout tabIndicator;
    private TextView skip;
    int position = 0 ;
    Button btnLogin , btnRegister;
    Animation btnAnim ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (restorePrefData()) {

//            Intent mainActivity = new Intent(getApplicationContext(),LoginActivity.class );
//            startActivity(mainActivity);
//            finish();


        }

        setContentView(R.layout.intro_activity);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(),LoginActivity.class );
                startActivity(mainActivity);
                finish();
            }
        });

        skip = findViewById(R.id.intro_skip_button);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class );
                Intent mainActivity = new Intent(getApplicationContext(),ChatActivity.class );
                startActivity(mainActivity);
                finish();
            }
        });

        btnRegister = findViewById(R.id.btn_register);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);

        final List<IntroModel> mList = new ArrayList<>();
        mList.add(new IntroModel("Fresh Food","Temukan pengalaman baru menemukan ","Temukan pengalaman baru menemukan " , R.drawable.icon_03));
        mList.add(new IntroModel("Fast Delivery","Lakon membuka peluang seluas-luasnya ","Temukan pengalaman baru menemukan " ,R.drawable.icon_04));
        mList.add(new IntroModel("Easy Payment","Interaksi penyedia part time ","Temukan pengalaman baru menemukan " ,R.drawable.icon_05));

        // setup viewpager
        screenPager =findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);



        tabIndicator.setupWithViewPager(screenPager);
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1) {

                    loaddLastScreen();

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // skip button click listener

//        tvSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                screenPager.setCurrentItem(mList.size());
//            }
//        });



    }

    private boolean restorePrefData() {

        Boolean isIntroActivityOpnendBefore = Prefs.getBoolean(PublicString.IS_INTRO_OPENED, false);

        return  isIntroActivityOpnendBefore;
    }

    private void savePrefsData() {
        Prefs.putBoolean(PublicString.IS_INTRO_OPENED, true);
    }

    // show the GETSTARTED Button and hide the indicator and the next button
    private void loaddLastScreen() {
//        final View touchView = findViewById(R.id.screen_viewpager);
//        touchView.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                return true;
//            }
//        });
//
    }
}
