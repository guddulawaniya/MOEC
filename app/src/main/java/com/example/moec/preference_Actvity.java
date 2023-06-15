package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.shuhart.stepview.StepView;

public class preference_Actvity extends AppCompatActivity {

    TextView index;
    StepView stepView;
    int stepcount=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_actvity);


        index  = findViewById(R.id.indexingtext);
        stepView = findViewById(R.id.step_view);

        progressbar(stepcount);

        Button nextbutton = findViewById(R.id.nextbutton);
        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stepcount--;

                if (stepcount>0)
                {
                    progressbar(stepcount);
                }
                else
                {
                    onBackPressed();

                }
            }
        });


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                stepcount++;
                if (stepcount<9)
                {
                    progressbar(stepcount);
                }

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


    void progressbar(int stepcount)
    {
        index.setText(stepcount+" of 7 ");
        stepView.getState()
                .animationType(StepView.ANIMATION_LINE)
                .nextStepLineColor(ContextCompat.getColor(getApplicationContext(), R.color.background_blue_shadew))
                .doneStepMarkColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .stepsNumber(8)
                .nextStepCircleEnabled(false)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(6)
                .commit();

        stepView.go(stepcount,true);
        switch (stepcount)
        {
            case 1:Page1 page1 = new Page1();
            replaceFragment(page1);
            break;
            case 2:Page2 page2 = new Page2();
            replaceFragment(page2);
            break;
            case 3:Page3 page3 = new Page3();
            replaceFragment(page3);
            break;
            case 4:Page4 page4 = new Page4();
            replaceFragment(page4);
            break;
            case 5:Page5 page5 = new Page5();
            replaceFragment(page5);
            break;
            case 6:Page6 page6 = new Page6();
            replaceFragment(page6);
            break;
            case 7:Page7 page7 = new Page7();
            replaceFragment(page7);
            break;
            case 8:startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            break;
        }


    }
}