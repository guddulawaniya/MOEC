package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.moec.BottomNavigation_Fragment.application_fragment;
import com.example.moec.BottomNavigation_Fragment.community_fragment;
import com.example.moec.BottomNavigation_Fragment.dashboard_fragment;
import com.example.moec.BottomNavigation_Fragment.insights_fragment;
import com.example.moec.BottomNavigation_Fragment.program_fragment;
import com.example.moec.JavaClass.InternetConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    TextView toolbartitle, textCartItemCount;
    int mCartItemCount = 100;
    ImageView  searchbar,profile_icon,profile_iconmain;
    LinearLayout searchelementLayouts;
    ImageView favorate;
    BottomNavigationView navigationView;
    ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config();

        progressBar = findViewById(R.id.progressBar);

        // internet file object and check

        InternetConnection nt = new InternetConnection(this);
        if (nt.isConnected()) {


            // finds the ids
            progressBar.setVisibility(View.GONE);
            searchelementLayouts = findViewById(R.id.searchfield);
            favorate = findViewById(R.id.favourate_icon_toolbar);
            toolbartitle = findViewById(R.id.toolbartitle);
            searchbar = findViewById(R.id.searchbar);
            profile_icon = findViewById(R.id.profile_icon);
            profile_iconmain = findViewById(R.id.profile_icon_toolbar);

            textCartItemCount = findViewById(R.id.notification_badge);
            navigationView = findViewById(R.id.bottomNavigationView);


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




            navigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
            navigationView.setSelectedItemId(R.id.dashboard);



            Intent intent = getIntent();

            int id = intent.getIntExtra("fmid", 0);

            if (id == 1) {
                replacefragment(program_fragment);
            }

            // set daata sharePreference

            SharedPreferences preferences = getSharedPreferences("registrationform", MODE_PRIVATE);
            String firstname = preferences.getString("Fname", "");
            String lastname = preferences.getString("Lname", "");
            String email = preferences.getString("email", "");
            studentname.setText(firstname + " " + lastname);

            sideemail.setText(email);

            String imageurl = preferences.getString("image",null);
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

        } else {
            View parentLayout = findViewById(R.id.drawerlayout);
            Snackbar snackbar = Snackbar.make(parentLayout, " Check Your Internet Connection !", Snackbar.LENGTH_INDEFINITE);

            snackbar.setAction("Open", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_DATA_ROAMING_SETTINGS);

                    startActivity(intent);

                    snackbar.dismiss();
                }
            });

            // Show the Snackbar
            snackbar.show();

        }
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
            searchelementLayouts.setVisibility(View.VISIBLE);
            favorate.setVisibility(View.VISIBLE);
            toolbartitle.setText("Dashboard");
            replacefragment(dashboard_fragment);
        } else if (R.id.program == item.getItemId()) {
            navigationView.setItemActiveIndicatorColor(ColorStateList.valueOf(getColor(R.color.primarycolor)));
            toolbartitle.setText("Program");
            favorate.setVisibility(View.VISIBLE);
            searchelementLayouts.setVisibility(View.VISIBLE);
            replacefragment(program_fragment);
        } else if (R.id.application == item.getItemId()) {
            navigationView.setItemTextAppearanceActive(R.style.BottomNavigationView);


            toolbartitle.setText("Application");
            replacefragment(application_fragment);
            searchelementLayouts.setVisibility(View.GONE);
            favorate.setVisibility(View.GONE);
        }
      /*  else if (R.id.community == item.getItemId()) {
            searchelementLayouts.setVisibility(View.GONE);

            favorate.setVisibility(View.GONE);
            toolbartitle.setText("Community");
            replacefragment(community_fragment);

        } */
        else if (R.id.insight == item.getItemId()) {

            toolbartitle.setText("Insight");
            searchelementLayouts.setVisibility(View.GONE);
            favorate.setVisibility(View.GONE);
            replacefragment(insights_fragment);

        }

        return true;
    }


    //
    private void config() {
        setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        getWindow().setSharedElementsUseOverlay(false);
    }

    private void replacefragment(Fragment fragment) {
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