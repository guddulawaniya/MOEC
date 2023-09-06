package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.TransitionManager;
import android.util.Base64;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.moec.BottomNavigation_Fragment.application_fragment;
import com.example.moec.BottomNavigation_Fragment.community_fragment;
import com.example.moec.BottomNavigation_Fragment.dashboard_fragment;
import com.example.moec.BottomNavigation_Fragment.insights_fragment;
import com.example.moec.BottomNavigation_Fragment.program_fragment;
import com.example.moec.JavaClass.InternetConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    TextView toolbartitle, textCartItemCount;
    int mCartItemCount = 100;
    ImageView searchbar, profile_icon, profile_iconmain;
    LinearLayout searchelementLayouts;
    private SwipeRefreshLayout swipeRefreshLayout;
    ImageView favorate;
    BottomNavigationView navigationView;

    InternetConnection nt;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config();


        // finds the ids

        searchelementLayouts = findViewById(R.id.searchfield);
        favorate = findViewById(R.id.favourate_icon_toolbar);
        toolbartitle = findViewById(R.id.toolbartitle);
        searchbar = findViewById(R.id.searchbar);
        profile_icon = findViewById(R.id.profile_icon);
        profile_iconmain = findViewById(R.id.profile_icon_toolbar);

        textCartItemCount = findViewById(R.id.notification_badge);
        navigationView = findViewById(R.id.bottomNavigationView);
        swipeRefreshLayout = findViewById(R.id.main_activity_effect_layout);


        TextView application = findViewById(R.id.sideapplication);
        TextView sideemail = findViewById(R.id.sideemail);
        TextView studentname = findViewById(R.id.studentname);
        TextView sidebarPreferenece = findViewById(R.id.sidebarPreferenece);
        TextView profiledetails = findViewById(R.id.profiledetails);
        LinearLayout notification = findViewById(R.id.notification);
        CardView profile = findViewById(R.id.profile);
        LinearLayout profilelinear = findViewById(R.id.profilelinear);
        TextView uploaddata = findViewById(R.id.sidedocument_upload);


        TextView sharelink = findViewById(R.id.sharelink);
        sharelink.setPaintFlags(sharelink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        nt = new InternetConnection(MainActivity.this);

        navigationView.setOnItemSelectedListener(this::onNavigationItemSelected);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Simulate a background task that takes some time (e.g., fetching data from the internet)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        nt = new InternetConnection(MainActivity.this);
                        swipeRefreshLayout.setRefreshing(false);

                        if (nt.isConnected()) {
                            swipeRefreshLayout.setVisibility(View.GONE);
                            navigationView.setSelectedItemId(R.id.dashboard);
                        }

                    }

                }, 1000);
            }
        });


        if (nt.isConnected()) {
            swipeRefreshLayout.setVisibility(View.GONE);

            navigationView.setSelectedItemId(R.id.dashboard);
        } else {
            Toast.makeText(MainActivity.this, "Unable Internet Connection", Toast.LENGTH_SHORT).show();
        }

        Intent intent = getIntent();

        int id = intent.getIntExtra("fmid", 0);

        if (id == 1) {
            replacefragment(program_fragment,0);
        }

        // set daata sharePreference

        SharedPreferences preferences = getSharedPreferences("registrationform", MODE_PRIVATE);
        String firstname = preferences.getString("Fname", "");
        String lastname = preferences.getString("Lname", "");
        String email = preferences.getString("email", "");
        studentname.setText(firstname + " " + lastname);

        sideemail.setText(email);

        String imageurl = preferences.getString("image", null);
        if (imageurl != null) {
            byte[] decodedBytes = Base64.decode(imageurl, Base64.DEFAULT);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

            // Display the decodedBitmap in an ImageView
            profile_icon.setImageBitmap(decodedBitmap);
            profile_iconmain.setImageBitmap(decodedBitmap);
        }


        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Search_Activity.class);
                overridePendingTransition(R.anim.right_in_activity, R.anim.left_out_activity);
                startActivity(intent);
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
                startActivity(intent1);

            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notification_Activity.class));
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draweropen();

            }
        });

        profilelinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), basic_details_activity.class));
            }
        });


        uploaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, My_documents_upload.class);
                startActivity(intent2);
            }
        });
        setupBadge();

    }

    void draweropen() {
        DrawerLayout drawerlayout = findViewById(R.id.drawerlayout);
        drawerlayout.openDrawer(Gravity.LEFT);
    }


    // object of fragments

    com.example.moec.BottomNavigation_Fragment.dashboard_fragment dashboard_fragment = new dashboard_fragment();
    com.example.moec.BottomNavigation_Fragment.program_fragment program_fragment = new program_fragment();
    com.example.moec.BottomNavigation_Fragment.application_fragment application_fragment = new application_fragment();
    com.example.moec.BottomNavigation_Fragment.community_fragment community_fragment = new community_fragment();
    com.example.moec.BottomNavigation_Fragment.insights_fragment insights_fragment = new insights_fragment();


    // bottom navigation functions

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (R.id.dashboard == item.getItemId()) {
            navigationView.setItemTextAppearanceActive(R.style.BottomNavigationView);
            toolbartitle.setText("Dashboard");
            replacefragment(dashboard_fragment,1);
        } else if (R.id.program == item.getItemId()) {
            navigationView.setItemActiveIndicatorColor(ColorStateList.valueOf(getColor(R.color.primarycolor)));
            toolbartitle.setText("Program");
            replacefragment(program_fragment,2);
        } else if (R.id.application == item.getItemId()) {
            navigationView.setItemTextAppearanceActive(R.style.BottomNavigationView);


            toolbartitle.setText("Application");
            replacefragment(application_fragment,3);
        }
      /*  else if (R.id.community == item.getItemId()) {
            searchelementLayouts.setVisibility(View.GONE);

            favorate.setVisibility(View.GONE);
            toolbartitle.setText("Community");
            replacefragment(community_fragment);

        } */
        else if (R.id.insight == item.getItemId()) {

            toolbartitle.setText("Insight");
            replacefragment(insights_fragment,4);

        }

        return true;
    }


    //
    private void config() {
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setSharedElementsUseOverlay(false);
    }
    int temp=0 ;
    private void replacefragment(Fragment fragment,int id) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (id>=temp)
        {
            transaction.setCustomAnimations(R.anim.right_in_activity, R.anim.left_out_activity);
        }
        else
        {
            transaction.setCustomAnimations(R.anim.left_in, R.anim.right_out);
        }
        temp=id;


        transaction.replace(R.id.Frame_laout, fragment);
        transaction.commit();


    }

    private void setupBadge() {
        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 10)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}