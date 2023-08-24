package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.moec.JavaClass.updateAPIcall;
import com.example.moec.Tabs_Adapters.program_details_tab_Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;
import com.squareup.picasso.Picasso;

public class Program_details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config();
        setContentView(R.layout.activity_program_details);


        TextView title = findViewById(R.id.toolbar_title);
        Button applyprogrambutton = findViewById(R.id.applyprogrambutton);
        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setVisibility(View.GONE);
        TabLayout tabLayout = findViewById(R.id.programtabs);
        ViewPager viewPager = findViewById(R.id.programviewpager);

        TextView coursename = findViewById(R.id.coursename);
        TextView universityname = findViewById(R.id.univeristyname);
        TextView countryname  = findViewById(R.id.countryname);
        ImageView universityimage  = findViewById(R.id.universityimage);
        ImageView favorateicon = findViewById(R.id.favorateicon);

        SharedPreferences preferences = getSharedPreferences("programdetails",MODE_PRIVATE);

        coursename.setText(preferences.getString("coursename",null));
        universityname.setText(preferences.getString("collegename",null));
        countryname.setText(preferences.getString("countryname",null));
        String courseid = preferences.getString("courseid",null);
        String favoratevalue = preferences.getString("favoratevalue",null);

        if (favoratevalue.equals("yes"))
        {
            favorateicon.setImageResource(R.drawable.favorite_heart);
        }
        else{
            favorateicon.setImageResource(R.drawable.favorite_icon);
        }



        Picasso.get()
                .load(preferences.getString("imageURL",null))
                .resize(300,100)
                .into(universityimage);

        applyprogrambutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Program_details.this, New_Application.class);
                startActivity(intent);

            }
        });

        favorateicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favoratevalue.isEmpty())
                {
                    favorateicon.setImageResource(R.drawable.favorite_heart);
                    new updateAPIcall(Program_details.this,courseid,"yes");
                }
                else{
                    favorateicon.setImageResource(R.drawable.favorite_icon);

                    new updateAPIcall(Program_details.this,courseid,"no");
                }
            }
        });

        program_details_tab_Adapter adapter = new program_details_tab_Adapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        // Setup tab layout
        tabLayout.setupWithViewPager(viewPager);

        title.setText("Program Details");

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

    private void config() {
        findViewById(android.R.id.content).setTransitionName("item");
        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.addTarget(android.R.id.content);
        transform.setDuration(500);
        getWindow().setSharedElementEnterTransition(transform);
        getWindow().setSharedElementReturnTransition(transform);

    }
}