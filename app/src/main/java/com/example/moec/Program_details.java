package com.example.moec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.moec.Tabs_Adapters.program_details_tab_Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

public class Program_details extends AppCompatActivity {

    int like= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        config();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_details);


        TextView title = findViewById(R.id.toolbar_title);
        Button applyprogrambutton = findViewById(R.id.applyprogrambutton);

        ImageView backbutton = findViewById(R.id.backbutton);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setVisibility(View.GONE);
        TabLayout tabLayout = findViewById(R.id.programtabs);
        ViewPager viewPager = findViewById(R.id.programviewpager);

        ImageView favorateicon = findViewById(R.id.favorateicon);

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
                if (like==0)
                {

                    like = 1;
                    favorateicon.setImageResource(R.drawable.favorite_heart);
                }
                else{
                    favorateicon.setImageResource(R.drawable.favorite_icon);
                    like = 0;
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