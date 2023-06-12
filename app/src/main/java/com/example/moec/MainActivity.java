package com.example.moec;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {



    TextView toolbartitle, textCartItemCount;
    int mCartItemCount=100;
    ImageView Refine;
    LinearLayout searchfield;
    ImageView favorate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


         searchfield = findViewById(R.id.searchfield);
         favorate = findViewById(R.id.favourate_icon_toolbar);
        Refine= findViewById(R.id.refine_icon);
        toolbartitle = findViewById(R.id.toolbartitle);


        Refine.setVisibility(View.GONE);


        LinearLayout notification = findViewById(R.id.notification);


        textCartItemCount = findViewById(R.id.notification_badge);
        TextView view_all  = findViewById(R.id.view_all);
        TextView application  = findViewById(R.id.sideapplication);
        TextView sidebarPreferenece  = findViewById(R.id.sidebarPreferenece);
        TextView profiledetails  = findViewById(R.id.profiledetails);

        Refine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Refine_Activity.class));
            }
        });
        sidebarPreferenece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), program_preference_Activity.class));
            }
        });
        application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), New_Application.class));
            }
        });
        profiledetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), profile_dashboard.class));
            }
        });


        favorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Favorate_Activity.class));

            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notification_Activity.class));
            }
        });




        CardView profile = findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draweropen();

            }
        });
        LinearLayout profilelinear = findViewById(R.id.profilelinear);
        profilelinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),basic_details_activity.class));
            }
        });

        TextView uploaddata = findViewById(R.id.sidedocument_upload);
        uploaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, My_documents_upload.class);
                startActivity(intent);
            }
        });




        BottomNavigationView navigationView = findViewById(R.id.bottomNavigationView);
        navigationView
                .setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.dashboard);
//
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.dashboard, R.id.program, R.id.application,R.id.community,R.id.insight)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.Frame_laout);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

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
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        if (R.id.dashboard==item.getItemId())
        {
            Refine.setVisibility(View.GONE);
            toolbartitle.setText("Dashboard");
            replacefragment(dashboard_fragment);
        }
        else if (R.id.program==item.getItemId())
        {
            Refine.setVisibility(View.VISIBLE);
            toolbartitle.setText("Program");
            replacefragment(program_fragment);
        }
        else if (R.id.application==item.getItemId()) {
            Refine.setVisibility(View.GONE);
            toolbartitle.setText("Application");
            replacefragment(application_fragment);
            searchfield.setVisibility(View.GONE);
            favorate.setVisibility(View.GONE);
        }
        else if (R.id.community==item.getItemId()) {
            Refine.setVisibility(View.GONE);
            favorate.setVisibility(View.GONE);
            toolbartitle.setText("Community");
            replacefragment(community_fragment);

        }
        else if (R.id.insight==item.getItemId()) {
            Refine.setVisibility(View.GONE);
            toolbartitle.setText("Insight");
            replacefragment(insights_fragment);

        }

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
//
//                return true;
//        }
        return false;
    }

    void replacefragment(Fragment fragment)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Frame_laout, fragment)
                .commit();
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}