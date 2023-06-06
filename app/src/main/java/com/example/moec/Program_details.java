package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class Program_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_details);
        getSupportActionBar().hide();
        TextView title = findViewById(R.id.toolbar_title);

        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setVisibility(View.GONE);
        TabLayout tabLayout = findViewById(R.id.programtabs);
        ViewPager viewPager = findViewById(R.id.programviewpager);

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
}