package com.example.moec.loginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.moec.Adapters.SliderAdapter;
import com.example.moec.R;
import com.smarteist.autoimageslider.SliderView;


public class view_Activity_before_login extends AppCompatActivity {


    CardView nextcirclebutton;
    int id;
    Button materialNestbbutton;
    ProgressBar progress;


    int[] images = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_before_login);

         progress = findViewById(R.id.progressBar);

         nextcirclebutton = findViewById(R.id.nextbuttonprogressbar);
        TextView skiptext = findViewById(R.id.skiptext);

         materialNestbbutton = findViewById(R.id.materialNestbbutton);

        SliderView sliderView = findViewById(R.id.slider);
        SliderAdapter adapterslider = new SliderAdapter(images);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapterslider);
        sliderView.setAutoCycle(false);
        int pageid = sliderView.getChildCount();


        id = sliderView.getCurrentPagePosition();

        materialNestbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view_Activity_before_login.this, login_Activity.class));
                finish();
            }
        });
        nextcirclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id++;
                if (id<4)
                {

                   sliderView.setCurrentPagePosition(id);
                   switch (id)
                   {
                       case 1:progress.setProgress(50);

                       break;
                       case 2: progress.setProgress(75);
                       break;
                       case 3: progress.setProgress(100);

                       nextcirclebutton.setVisibility(View.GONE);
                       materialNestbbutton.setVisibility(View.VISIBLE);
                       skiptext.setVisibility(View.GONE);

                       break;
                   }

                }

            }
        });
        skiptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id < 4)
                {
                    sliderView.setCurrentPagePosition(3);
                    nextcirclebutton.setVisibility(View.GONE);
                    materialNestbbutton.setVisibility(View.VISIBLE);
                    progress.setProgress(100);
                }


            }
        });

    }


}

