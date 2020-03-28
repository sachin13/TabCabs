package tabcabs.in.tabcabs;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import tabcabs.in.tabcabs.CabTabFragment.Advertisement;
import tabcabs.in.tabcabs.CabTabFragment.Analytics;
import tabcabs.in.tabcabs.CabTabFragment.Contact_Us;
import tabcabs.in.tabcabs.CabTabFragment.DashBoardFragment;
import tabcabs.in.tabcabs.CabTabFragment.Profile;
import tabcabs.in.tabcabs.CabTabFragment.Setting;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FrameLayout layout;
    android.support.v4.app.FragmentManager fragmentManager;
    NavigationView navigationView,nav_view_admin;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    ImageView circularImageView;
    TextView tv_username;

    private int count=0;


    String  name,desc,Notification,user_mode,Society_No;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        layout = (FrameLayout) findViewById(R.id.fragment_container);
        layout.setVisibility(View.GONE);


       init();





    }


    private void init() {


        navigationView = (NavigationView) findViewById(R.id.nav_view);



        navigationView.setVisibility(View.VISIBLE);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main_drawer_acticity);
        circularImageView = (ImageView)headerView.findViewById(R.id.circleView);


        tv_username=(TextView)headerView.findViewById(R.id.tv_username);




        callDashBoard();





    }














    private void callDashBoard() {

        //member dashboard



        DashBoardFragment hello = new DashBoardFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).commit();
        layout.setVisibility(View.VISIBLE);



    }














    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onBackPressed() {


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        Fragment f =getSupportFragmentManager().findFragmentById(R.id.fragment_container);


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (f instanceof DashBoardFragment) {


            final Dialog dialog = new Dialog(MainActivity.this);


            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog1);


            dialog.show();

            Button declineButton1 = (Button) dialog.findViewById(R.id.btn_no);

            Button declineButton = (Button) dialog.findViewById(R.id.btn_yes);
            // if decline button is clicked, close the custom dialog
            declineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    finish();
                }
            });

            declineButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close dialog
                    dialog.dismiss();
                }
            });



        }else{
            DashBoardFragment dashhello = new DashBoardFragment();

            fragmentManager.beginTransaction().replace(R.id.fragment_container, dashhello).commit();
        }





























    }














    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.Home) {

            DashBoardFragment hello = new DashBoardFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).addToBackStack(null).commit();
            layout.setVisibility(View.VISIBLE);



        }

        else if (id == R.id.Profile) {

            Profile hello = new Profile();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).addToBackStack(null).commit();
            layout.setVisibility(View.VISIBLE);



        }else if (id == R.id.Advertisement) {

            Advertisement hello = new Advertisement();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).addToBackStack(null).commit();
            layout.setVisibility(View.VISIBLE);



        }else if (id == R.id.Analytics) {

            Analytics hello = new Analytics();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).addToBackStack(null).commit();
            layout.setVisibility(View.VISIBLE);



        }else if (id == R.id.Setting) {

            Setting hello = new Setting();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).addToBackStack(null).commit();
            layout.setVisibility(View.VISIBLE);



        }
        else if (id == R.id.Contact_Us) {

            Contact_Us hello = new Contact_Us();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, hello).addToBackStack(null).commit();
            layout.setVisibility(View.VISIBLE);



        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
