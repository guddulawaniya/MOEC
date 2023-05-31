package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class preference_Actvity extends AppCompatActivity {

    int id=1;
    TextView index;
    int indexingid =1;
    View view1,view2,view3,view4,view5,view6,view7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_actvity);


        index  = findViewById(R.id.indexingtext);
        Button nextbutton = findViewById(R.id.nextbutton);
        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id>1)
                {
                    id--;
                    index.setText(id+" of 7");
                    onclicksetfragment(id);
                }
                else
                {
                    onBackPressed();

                }
            }
        });

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        view5 = findViewById(R.id.view5);
        view6 = findViewById(R.id.view6);
        view7 = findViewById(R.id.view7);
        onclicksetfragment(id);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id<8)
                {
                    id++;
                    index.setText(id+" of 7");
                    onclicksetfragment(id);
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

    public void onclicksetfragment(int id) {
        switch (id)
        {
            case 1: Page1 page1 = new Page1();
                replaceFragment(page1);
                index.setText(id+" of 7");
                view2.setBackgroundColor(Color.parseColor("#e7eef6"));
                view1.setBackgroundColor(Color.parseColor("#1a519e"));
                break;
            case 2: Page2 page2 = new Page2();
                replaceFragment(page2);
                view3.setBackgroundColor(Color.parseColor("#e7eef6"));
                view2.setBackgroundColor(Color.parseColor("#1a519e"));

                break;
            case 3: Page3 page3 = new Page3();
                replaceFragment(page3);
                view4.setBackgroundColor(Color.parseColor("#e7eef6"));
                view3.setBackgroundColor(Color.parseColor("#1a519e"));

                break;
            case 4: Page4 page4 = new Page4();
                replaceFragment(page4);
                view5.setBackgroundColor(Color.parseColor("#e7eef6"));

                view4.setBackgroundColor(Color.parseColor("#1a519e"));
                break;
            case 5: Page5 page5 = new Page5();
                replaceFragment(page5);
                view6.setBackgroundColor(Color.parseColor("#e7eef6"));
                view5.setBackgroundColor(Color.parseColor("#1a519e"));
                break;
            case 6: Page6 page6 = new Page6();
                replaceFragment(page6);
                view7.setBackgroundColor(Color.parseColor("#e7eef6"));
                view6.setBackgroundColor(Color.parseColor("#1a519e"));
                break;
            case 7: Page7 page7 = new Page7();
                view7.setBackgroundColor(Color.parseColor("#1a519e"));
                replaceFragment(page7);
                break;
            case 8:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
        }
    }
}