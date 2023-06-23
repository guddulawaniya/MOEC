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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.loginbeforeimage_Adapter;
import com.example.moec.Adapters.slidermodule;
import com.example.moec.R;

import java.util.ArrayList;


public class view_Activity_before_login extends AppCompatActivity  {


    CardView nextcirclebutton;
    int id;
    Button materialNestbbutton;
    ProgressBar progress;
    TextView skiptext;
    SwipeGesturesanimation SwipeGesturesanimation;


    RecyclerView sliderRecyclerview;

    int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};

    ArrayList<slidermodule> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_before_login);

        list = new ArrayList<>();

        list.add(new slidermodule(R.drawable.image1,"Study your favorite course in \nTop Universities"));
        list.add(new slidermodule(R.drawable.image2,"Track your Application Progress"));
        list.add(new slidermodule(R.drawable.image3,"Get guaranteed offer letter"));
        list.add(new slidermodule(R.drawable.image4,"Be a part of community \n of 10 lakh+ students"));

        progress = findViewById(R.id.progressBar);
        sliderRecyclerview = findViewById(R.id.sliderRecyclerview);
        sliderRecyclerview.setNestedScrollingEnabled(false);






        nextcirclebutton = findViewById(R.id.nextbuttonprogressbar);
         skiptext = findViewById(R.id.skiptext);

        materialNestbbutton = findViewById(R.id.materialNestbbutton);


        sliderRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        loginbeforeimage_Adapter adapterslider = new loginbeforeimage_Adapter(list,this);
        sliderRecyclerview.setAdapter(adapterslider);
        sliderRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int position = getCurrentItem();
                    onPageChanged(position);
                }
            }
        });
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(sliderRecyclerview);




//        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
//        sliderView.setSliderAdapter(adapterslider);
//        sliderView.setAutoCycle(false);


//        SwipeGesturesanimation = new SwipeGesturesanimation(sliderView);


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

                next();


            }
        });
        skiptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (id < 4) {
                    setCurrentItem(4,true);
                }


            }
        });

    }


    void onPageChanged(int id)
    {
        if (id < 4) {


            nextcirclebutton.setVisibility(View.VISIBLE);
            materialNestbbutton.setVisibility(View.INVISIBLE);
            skiptext.setVisibility(View.VISIBLE);
            switch (id) {

                case 0:
                    progress.setProgress(25);
                    break;
                    case 1:
                    progress.setProgress(50);
                    break;
                case 2:
                    progress.setProgress(75);

                    break;
                case 3:
                    progress.setProgress(100);
                    nextcirclebutton.setVisibility(View.INVISIBLE);
                    materialNestbbutton.setVisibility(View.VISIBLE);
                    skiptext.setVisibility(View.INVISIBLE);
                    break;
            }

        }

    }
    public boolean hasPreview() {
        return getCurrentItem() > 0;
    }

    public boolean hasNext() {
        return sliderRecyclerview.getAdapter() != null &&
                getCurrentItem() < (sliderRecyclerview.getAdapter().getItemCount()- 1);
    }

    public void preview() {
        int position = getCurrentItem();
        if (position > 0)
            setCurrentItem(position -1, true);
    }

    public void next() {
        RecyclerView.Adapter adapter = sliderRecyclerview.getAdapter();
        if (adapter == null)
            return;

        int position = getCurrentItem();
        int count = adapter.getItemCount();
        if (position < (count -1))
            setCurrentItem(position + 1, true);
    }


    private int getCurrentItem(){
        return ((LinearLayoutManager)sliderRecyclerview.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    private void setCurrentItem(int position, boolean smooth){
        if (smooth)
            sliderRecyclerview.smoothScrollToPosition(position);
        else
            sliderRecyclerview.scrollToPosition(position);
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

