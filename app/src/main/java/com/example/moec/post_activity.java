package com.example.moec;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class post_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        TextView title = findViewById(R.id.toolbar_title);
        ImageView canclebutton = findViewById(R.id.backbutton);


        canclebutton.setImageResource(R.drawable.close_icon);



        canclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        title.setText("Add Post");
    }
}