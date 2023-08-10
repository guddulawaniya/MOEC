package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.moec.BottomNavigation_Fragment.application_fragment;
import com.example.moec.BottomNavigation_Fragment.community_fragment;
import com.example.moec.BottomNavigation_Fragment.dashboard_fragment;
import com.example.moec.BottomNavigation_Fragment.insights_fragment;
import com.example.moec.BottomNavigation_Fragment.program_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

public class MainActivity extends AppCompatActivity   implements BottomNavigationView.OnNavigationItemSelectedListener {



    TextView toolbartitle, textCartItemCount;
    int mCartItemCount=100;
    ImageView Refine,searchbar;
    LinearLayout searchfield;
    ImageView favorate;
    BottomNavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        config();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         searchfield = findViewById(R.id.searchfield);
         favorate = findViewById(R.id.favourate_icon_toolbar);
        Refine= findViewById(R.id.refine_icon);
        toolbartitle = findViewById(R.id.toolbartitle);
        searchbar = findViewById(R.id.searchbar);


        Refine.setVisibility(View.GONE);
        navigationView = findViewById(R.id.bottomNavigationView);

        LinearLayout notification = findViewById(R.id.notification);
        navigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
        navigationView.setSelectedItemId(R.id.dashboard);


        textCartItemCount = findViewById(R.id.notification_badge);
        TextView application  = findViewById(R.id.sideapplication);
        TextView studentname  = findViewById(R.id.studentname);
        TextView sidebarPreferenece  = findViewById(R.id.sidebarPreferenece);
        TextView profiledetails  = findViewById(R.id.profiledetails);
        Intent intent = getIntent();
        int id = intent.getIntExtra("fmid",0);
        if (id==1)
        {
            replacefragment(program_fragment);
        }
        else if (id==2)
        {
            replacefragment(program_fragment);

        }

        // set daata sharePreference

        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);
        String firstname = preferences.getString("Fname","");
        String lastname =   preferences.getString("Lname","");
        studentname.setText(firstname+" "+lastname);
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(MainActivity.this, Search_Activity.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,searchbar,"search").toBundle();
                startActivity(intent1,bundle);
            }
        });




        // share with friends app
        LinearLayout sharefrieds = findViewById(R.id.refer_friends);

        TextView sharelink = findViewById(R.id.sharelink);
        sharelink.setPaintFlags(sharelink.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        sharefrieds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendlinkwithfriends();

                } catch(Exception e) {
                    e.toString();
                }

            }
        });
        sharelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendlinkwithfriends();

                } catch(Exception e) {
                    e.toString();
                }

            }
        });

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
                finish();
            }
        });


        favorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(MainActivity.this, Favorate_Activity.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,searchbar,"favorate").toBundle();
                startActivity(intent1,bundle);

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




//         navigationView = findViewById(R.id.bottomNavigationView);
//        navigationView
//                .setOnNavigationItemSelectedListener(this);
//        navigationView.setSelectedItemId(R.id.dashboard);
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




    com.example.moec.BottomNavigation_Fragment.dashboard_fragment dashboard_fragment = new dashboard_fragment();
    com.example.moec.BottomNavigation_Fragment.program_fragment program_fragment = new program_fragment();
    com.example.moec.BottomNavigation_Fragment.application_fragment application_fragment = new application_fragment();
    com.example.moec.BottomNavigation_Fragment.community_fragment community_fragment = new community_fragment();
    com.example.moec.BottomNavigation_Fragment.insights_fragment insights_fragment = new insights_fragment();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        if (R.id.dashboard==item.getItemId())
        {
            navigationView.setItemTextAppearanceActive(R.style.BottomNavigationView);
            Refine.setVisibility(View.GONE);
            searchfield.setVisibility(View.VISIBLE);
            favorate.setVisibility(View.VISIBLE);
            toolbartitle.setText("Dashboard");
            replacefragment(dashboard_fragment);
        }

        else if (R.id.program==item.getItemId())
        {
           navigationView.setItemActiveIndicatorColor(ColorStateList.valueOf(getColor(R.color.primarycolor)));
            Refine.setVisibility(View.VISIBLE);
            toolbartitle.setText("Program");
            favorate.setVisibility(View.VISIBLE);
            searchfield.setVisibility(View.VISIBLE);
            replacefragment(program_fragment);
        }
        else if (R.id.application==item.getItemId()) {
            navigationView.setItemTextAppearanceActive(R.style.BottomNavigationView);

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
            searchfield.setVisibility(View.GONE);
            favorate.setVisibility(View.GONE);
            replacefragment(insights_fragment);

        }

        return true;
    }

   private void config() {
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setSharedElementsUseOverlay(false);
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

    void sendlinkwithfriends()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share with Friends");
        String shareMessage= "\nLet me recommend you this application\n\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Choose on Application"));
    }
}