package men.ngopi.zakwan.jasanesia;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import men.ngopi.zakwan.jasanesia.Aktivitas.AktivitasFragment;
import men.ngopi.zakwan.jasanesia.Akun.AkunFragment;
import men.ngopi.zakwan.jasanesia.Dashboard.DashboardFragment;
import men.ngopi.zakwan.jasanesia.Dashboard.DashboradMainFragment;
import men.ngopi.zakwan.jasanesia.Pesan.PesanFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    Fragment fragment1 = new DashboradMainFragment();
    Fragment fragment2 = new AktivitasFragment();
    Fragment fragment3 = new PesanFragment();
    Fragment fragment4 = new AkunFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    int fragmentID;

    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){

            fm.beginTransaction().add(R.id.fl_container, fragment3, "3").hide(fragment3).commit();
            fm.beginTransaction().add(R.id.fl_container, fragment2, "2").hide(fragment2).commit();
            fm.beginTransaction().add(R.id.fl_container,fragment1, "1").commit();
            fm.beginTransaction().add(R.id.fl_container, fragment4, "4").hide(fragment4).commit();
            fragmentID = R.id.home_menu;
        }


        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bn_main);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Home", R.drawable.ic_dashboard_black_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("History", R.drawable.ic_assignment_black_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Pesan", R.drawable.ic_main_menu_message, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Akun", R.drawable.ic_main_menu_profile, R.color.colorPrimary);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        bottomNavigation.setPadding(0 , 10 ,0 ,10);

        bottomNavigation.setAccentColor(getResources().getColor( R.color.colorPrimary));

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        bottomNavigation.setBehaviorTranslationEnabled(false);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                int id = R.id.home_menu;

                switch (position){
                    case 0 : {
                        id = R.id.home_menu;
                        break;
                    }
                    case 1 : {
                        id = R.id.Aktivitas_menu;
                        break;
                    }
                    case 2 : {
                        id = R.id.Pesan_menu;
                        break;
                    }
                    case 3 : {
                        id = R.id.Akun_menu;
                        break;
                    }
                }

                handleNavigation(id);
                return true;
            }
        });

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();

//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.replace(R.id.fl_container, fragment);
//            transaction.commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        return handleNavigation(item.getItemId());
    }

    public boolean handleNavigation(int id){
        switch (id){
            case R.id.home_menu:
//                if(fragmentID == R.id.home_menu){
//                    fragment1 = new DashboradMainFragment();
//                    fm.beginTransaction().remove(active).add(R.id.fl_container , fragment1 , "1").commit();
//                }else {
//                    fm.beginTransaction().hide(active).show(fragment1).commit();
//                }

                fm.beginTransaction().hide(active).show(fragment1).commit();
                active = fragment1;
                fragmentID = R.id.home_menu;
                return true;
            case R.id.Aktivitas_menu:
                if(fragmentID == R.id.Aktivitas_menu){
                    fragment2 = new AktivitasFragment();
                    fm.beginTransaction().remove(active).add(R.id.fl_container , fragment2 , "2").commit();
                }else {
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                }
                active = fragment2;
                fragmentID = R.id.Aktivitas_menu;
                return true;
            case R.id.Pesan_menu:
                if(fragmentID == R.id.Pesan_menu){
                    fragment3 = new PesanFragment();
                    fm.beginTransaction().remove(active).add(R.id.fl_container , fragment3 , "3").commit();
                }else {
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                }
                active = fragment3;
                fragmentID = R.id.Pesan_menu;
                return true;
            case R.id.Akun_menu:
                if(fragmentID == R.id.Akun_menu){
                    fragment4 = new AkunFragment();
                    fm.beginTransaction().remove(active).add(R.id.fl_container , fragment4 , "4").commit();
                }else {
                    fm.beginTransaction().hide(active).show(fragment4).commit();
                }
                active = fragment4;
                fragmentID = R.id.Akun_menu;
                return true;
        }
        return false;
    }

    public AHBottomNavigation getBottomNavigation() {
        return bottomNavigation;
    }
}
