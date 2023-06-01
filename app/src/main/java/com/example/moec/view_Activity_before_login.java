package com.example.moec;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class view_Activity_before_login extends AppCompatActivity {


    int id=0;
    CardView nextcirclebutton;
    Button materialNestbbutton;
    ProgressBar progressBar,progress;

    View view2,view3,view4;

    View  view1;
    TextView welcomehint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_before_login);
        getSupportActionBar().hide();

         progress = findViewById(R.id.progressBar);
         progress.setProgress(ContextCompat.getColor(this, R.color.secondarycolor));

         FrameLayout frame = findViewById(R.id.viewframelayout);

         nextcirclebutton = findViewById(R.id.nextbuttonprogressbar);
        TextView skiptext = findViewById(R.id.skiptext);
         welcomehint = findViewById(R.id.welcomehint);
        progressBar = findViewById(R.id.progress_bar);
         materialNestbbutton = findViewById(R.id.materialNestbbutton);
            view1 = findViewById(R.id.view1);
            view2 = findViewById(R.id.view2);
            view3 = findViewById(R.id.view3);
            view4 = findViewById(R.id.view4);



        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliderfragment(2);
            }
        });
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliderfragment(3);
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliderfragment(1);
            }
        });
 view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sliderfragment(0);
            }
        });


        sliderfragment(id);


        materialNestbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view_Activity_before_login.this, login_Activity.class));
            }
        });
        nextcirclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id<4)
                {
                    id++;
                    sliderfragment(id);
                }

            }
        }); skiptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id<4)
                    sliderfragment(3);

            }
        });


    }

    void replaceFragment(Fragment fragment)
    {
        FragmentManager manager = getSupportFragmentManager();
        androidx.fragment.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.viewframelayout,fragment);
        fragmentTransaction.commit();
    }


    void changeindotorcolor(int id )
    {
        switch (id){
            case 0:view1.setBackgroundColor(ContextCompat.getColor(this, R.color.primarycolor));
                view2.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                view3.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                view4.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                progressBar.setProgress(25);
                view1.setMinimumWidth(20);
                view1.setMinimumHeight(20);
            break;
            case 1:
                view2.setBackgroundColor(ContextCompat.getColor(this, R.color.primarycolor));
                view1.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                view3.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                view4.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                progressBar.setProgress(50);
                break;
            case 2:view3.setBackgroundColor(ContextCompat.getColor(this, R.color.primarycolor));
                view1.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                view2.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                view4.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                progressBar.setProgress(75);
            break;
            case 3:view4.setBackgroundColor(ContextCompat.getColor(this, R.color.primarycolor));
                view1.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                view3.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                view2.setBackgroundColor(ContextCompat.getColor(this, R.color.background_blue_shadew));
                progressBar.setProgress(100);
            break;
        }


    }
    void sliderfragment(int id )
    {

        switch (id)
        {
            case 0:
                viewpage1 page1 = new viewpage1();

                changeindotorcolor(id);
                overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                replaceFragment(page1);
                dotchangecolorafterclick();
                break;
            case 1:
                viewpage2 page2 = new viewpage2();
                changeindotorcolor(id);
                overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                replaceFragment(page2);
                dotchangecolorafterclick();
                break;
            case 2:
                viewpage3 page3 = new viewpage3();
                changeindotorcolor(id);

                dotchangecolorafterclick();
                overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                replaceFragment(page3);
                break;
            case 3:
                viewpage4 page4 = new viewpage4();
                overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                replaceFragment(page4);
                changeindotorcolor(id);

                progressBar.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progress.setVisibility(View.GONE);
                        materialNestbbutton.setVisibility(View.VISIBLE);
                        nextcirclebutton.setVisibility(View.GONE);
                        welcomehint.setVisibility(View.VISIBLE);


                    }
                },1000);

                break;
        }
    }


    void dotchangecolorafterclick()
    {
        progressBar.setVisibility(View.VISIBLE);
        materialNestbbutton.setVisibility(View.GONE);
        nextcirclebutton.setVisibility(View.VISIBLE);
        welcomehint.setVisibility(View.GONE);
    }

}

