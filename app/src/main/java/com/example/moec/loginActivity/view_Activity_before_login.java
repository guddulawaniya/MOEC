package com.example.moec.loginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.moec.Adapters.SliderAdapter;
import com.example.moec.R;
import com.smarteist.autoimageslider.SliderView;


public class view_Activity_before_login extends AppCompatActivity  {


    CardView nextcirclebutton;
    int id;
    Button materialNestbbutton;
    ProgressBar progress;
    TextView skiptext;
    SwipeGesturesanimation SwipeGesturesanimation;
ConstraintLayout slidercontainer;

    int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_before_login);

        progress = findViewById(R.id.progressBar);
        slidercontainer = findViewById(R.id.slidercontainer);

        nextcirclebutton = findViewById(R.id.nextbuttonprogressbar);
         skiptext = findViewById(R.id.skiptext);

        materialNestbbutton = findViewById(R.id.materialNestbbutton);

        SliderView sliderView = findViewById(R.id.slider);
        SliderAdapter adapterslider = new SliderAdapter(images);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapterslider);
        sliderView.setAutoCycle(false);

        id = sliderView.getCurrentPagePosition();


        SwipeGesturesanimation = new SwipeGesturesanimation(sliderView);

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
                if (id < 4) {

                    sliderView.setCurrentPagePosition(id);
                    switch (id) {
                        case 1:
                            progress.setProgress(50);

                            break;
                        case 2:
                            progress.setProgress(75);
                            break;
                        case 3:
                            progress.setProgress(100);

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

                if (id < 4) {
                    sliderView.setCurrentPagePosition(3);
                    nextcirclebutton.setVisibility(View.GONE);
                    materialNestbbutton.setVisibility(View.VISIBLE);
                    progress.setProgress(100);
                }


            }
        });

    }


    private class SwipeGesturesanimation implements View.OnTouchListener {
        GestureDetector gestureDetector;

        SwipeGesturesanimation(View view) {
            int threshold = 100;
            int velocity_threshold = 100;

            GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onDown(@NonNull MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                    float xdiff = e2.getX() - e1.getX();
                    float ydiff = e2.getY() - e1.getY();

                    try {
                        if (Math.abs(xdiff) > Math.abs(ydiff)) {
                            if (Math.abs(xdiff) > threshold && Math.abs(velocityX) > velocity_threshold) {

                                if (xdiff > 0) {
                                    skiptext.setText("Swipe Right ");
                                    Toast.makeText(view.getContext(), "Swipe right", Toast.LENGTH_SHORT).show();
                                } else {
                                    skiptext.setText("Swipe Left ");
                                    Toast.makeText(view.getContext(), "Swipe Left", Toast.LENGTH_SHORT).show();

                                }
                                return true;
                            }
                        }

                        else {
                            if (Math.abs(ydiff) > threshold && Math.abs(velocityY) > velocity_threshold) {
                                if (ydiff > 0) {
                                    skiptext.setText("Swipe Down ");
                                    Toast.makeText(view.getContext(), "Swipe Down", Toast.LENGTH_SHORT).show();
                                } else {
                                    skiptext.setText("Swipe Top ");
                                    Toast.makeText(view.getContext(), "Swipe Top", Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            };

            gestureDetector = new GestureDetector(listener);
            view.setOnTouchListener(this);
        }


        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return gestureDetector.onTouchEvent(motionEvent);
        }
    }
}

