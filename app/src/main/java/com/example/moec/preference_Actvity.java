package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class preference_Actvity extends AppCompatActivity {

    int id=0;
    FrameLayout frameLayout;
    View view1,view2,view3,view4,view5,view6,view7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_actvity);

        frameLayout = findViewById(R.id.framelayout);
        Button nextbutton = findViewById(R.id.nextbutton);

        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        view5 = findViewById(R.id.view5);
        view6 = findViewById(R.id.view6);
        view7 = findViewById(R.id.view7);
        onclicksetfragment();

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id++;
                onclicksetfragment();



            }
        });

    }




    void replaceFragment(Fragment fragment)
    {
        FragmentManager manager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.commit();
    }

    public void onclicksetfragment() {
        switch (id)
        {
            case 0: Page1 page1 = new Page1();
                replaceFragment(page1);
                view1.setBackgroundColor(Color.RED);
                break;
            case 1: Page2 page2 = new Page2();
                replaceFragment(page2);
                view2.setBackgroundColor(Color.RED);
                break;
            case 2: Page3 page3 = new Page3();
                replaceFragment(page3);
                view3.setBackgroundColor(Color.RED);
                break;
            case 3: Page4 page4 = new Page4();
                replaceFragment(page4);
                view4.setBackgroundColor(Color.RED);
                break;
            case 4: Page5 page5 = new Page5();
                replaceFragment(page5);
                view5.setBackgroundColor(Color.RED);
                break;
            case 5: Page6 page6 = new Page6();
                replaceFragment(page6);
                view6.setBackgroundColor(Color.RED);
                break;
            case 6: Page7 page7 = new Page7();
                view7.setBackgroundColor(Color.RED);
                replaceFragment(page7);
                break;
            case 7:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
        }
    }
}