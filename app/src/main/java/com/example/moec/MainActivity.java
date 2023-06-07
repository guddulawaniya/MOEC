package com.example.moec;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();

        CardView profile = findViewById(R.id.profile);
        ImageView Refine = findViewById(R.id.refine_icon);
        TextView toolbartitle = findViewById(R.id.toolbartitle);
        toolbartitle.setText("Dashboard");

        Refine.setVisibility(View.GONE);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                draweropen();

            }
        });




        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.dashboard, R.id.program, R.id.application,R.id.community,R.id.insight)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.Frame_laout);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }


    void draweropen()
    {
        DrawerLayout drawerlayout = findViewById(R.id.drawerlayout);

        drawerlayout.openDrawer(Gravity.LEFT);
    }


    dashboard_fragment dashboard_fragment = new dashboard_fragment();
    program_fragment program_fragment = new program_fragment();
    application_fragment application_fragment = new application_fragment();
    community_fragment community_fragment = new community_fragment();
    insights_fragment insights_fragment = new insights_fragment();
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.dashboard:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.framelayout, dashboard_fragment)
//                        .commit();
//                return true;
//
//            case R.id.program:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.framelayout, program_fragment)
//                        .commit();
//                return true;
//
//            case R.id.application:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.framelayout, application_fragment)
//                        .commit();
//                return true;
//            case R.id.community:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.framelayout, community_fragment)
//                        .commit();
//                return true;
//
//            case R.id.insight:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.framelayout, insights_fragment)
//                        .commit();
//                return true;
//        }
//        return false;
//    }
}