package com.example.moec.loginActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.moec.MainActivity;
import com.example.moec.R;

import java.util.ArrayList;


public class view_Activity_before_login extends AppCompatActivity  {


    CardView nextcirclebutton;
    int id;
    Button materialNestbbutton;
    ProgressBar progress;
    TextView skiptext;


    RecyclerView sliderRecyclerview;
    String userid;


    ArrayList<slidermodule> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_before_login);

        SharedPreferences sharedPreferences = getSharedPreferences("registrationform", Context.MODE_PRIVATE);

        userid = sharedPreferences.getString("userid",null);

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



        materialNestbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view_Activity_before_login.this, login_Activity.class));
                overridePendingTransition(R.anim.right_in_activity, R.anim.left_out_activity);
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

    @Override
    protected void onStart() {
        super.onStart();
        if (userid!=null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            overridePendingTransition(R.anim.right_in_activity, R.anim.left_out_activity);
            finish();
        }
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

}

