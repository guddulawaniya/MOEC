package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.most_prefered_destination_Adapter;
import com.example.moec.LoginAfter_Steps.Page1;
import com.example.moec.LoginAfter_Steps.Page2;
import com.example.moec.LoginAfter_Steps.Page3;
import com.example.moec.LoginAfter_Steps.Page4;
import com.example.moec.LoginAfter_Steps.Page5;
import com.example.moec.LoginAfter_Steps.Page7;
import com.example.moec.ModulesClass.most_prefered_destination_module;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class preference_Actvity extends AppCompatActivity {

    TextView index;
    StepView stepView;
    int stepcount=1;
    Button nextbutton;
    ArrayList<most_prefered_destination_module> mostpreferedlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_actvity);


        index  = findViewById(R.id.indexingtext);
        stepView = findViewById(R.id.step_view);

         nextbutton = findViewById(R.id.nextbutton);
        ImageView backbutton = findViewById(R.id.backbutton);
        mostpreferedlist = new ArrayList<>();

        RecyclerView mostpreferedRecyclerview =  findViewById(R.id.mostpreferedRecyclerview);
        mostpreferedRecyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));


        // nested scolling set false
        mostpreferedRecyclerview.setNestedScrollingEnabled(false);


        progressbar(stepcount);

        // object Adapters
        most_prefered_destination_Adapter mostAdapter = new most_prefered_destination_Adapter(mostpreferedlist);


        // set adapter on recycler view

        mostpreferedRecyclerview.setAdapter(mostAdapter);


        // add data on list
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.uk_flag,"United Kingdom"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.us_flag,"United State"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.flag_canada,"Canada"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.australia_flag,"Australia"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.flag_canada,"Italy"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.germany_flag,"Germany"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.zealand_flag,"new Zealand"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.dubai_flag,"Dubai"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.poland_flag,"Poland"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.ireland_flag,"Ireland"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.latvia_flag,"Latvia"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.mauritius_flg,"Mauritius"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.malta_flag,"Malta"));


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

                SharedPreferences.Editor editor = getSharedPreferences("changedFragments",MODE_PRIVATE).edit();
                editor.putString("Fragmentid", String.valueOf(stepcount));
                editor.commit();

                Page2 page1 = new Page2();

                replaceFragment(page1);


            }
        });

    }


    void replaceFragment(Fragment fragment)
    {
        FragmentManager manager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }


    void progressbar(int stepcount)
    {
        index.setText(stepcount+" of 7 ");
        stepView.getState()
                .animationType(StepView.ANIMATION_LINE)
                .nextStepLineColor(ContextCompat.getColor(getApplicationContext(), R.color.background_blue_shadew))
                .doneStepMarkColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .stepsNumber(7)
                .nextStepCircleEnabled(false)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(6)
                .commit();

        stepView.go(stepcount,true);




        switch (stepcount)
        {
            case 1:
                Page1 page1 = new Page1();
            replaceFragment(page1);
            break;
            case 2:
                Page2 page2 = new Page2();
            replaceFragment(page2);
            break;
            case 3:
                Page3 page3 = new Page3();
            replaceFragment(page3);
            break;
            case 4:
                Page4 page4 = new Page4();

            replaceFragment(page4);
            break;
            case 5:
                Page5 page5 = new Page5();

            replaceFragment(page5);
            break;
            case 6:
                Page7 page7 = new Page7();
            replaceFragment(page7);

            break;
            case 7:startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            break;
        }


    }

    @Override
    public void onBackPressed() {
        stepcount--;

        if (stepcount==1)
        {
            super.onBackPressed();
        }
        else
        {

            progressbar(stepcount);

        }



    }
}