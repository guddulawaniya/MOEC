package com.example.moec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;

public class Check_password extends AppCompatActivity {

    int id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_password);

        PinView password = findViewById(R.id.pinview);
        Button verifybutton = findViewById(R.id.verifybuttonotp);
        password.requestFocus();

        SharedPreferences editor = getSharedPreferences("preference",MODE_PRIVATE);
      String pass = editor.getString("password",null);


      verifybutton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              if (pass.equals(password.getText().toString()))
              {
                  startActivity(new Intent(getApplicationContext(), MainActivity.class));
                  finish();

              }
              else if (id==2) {
                  Toast toast=   Toast.makeText(Check_password.this, "Your PIN "+pass, Toast.LENGTH_SHORT);
                  toast.show();

              }
              else
              {
                  id++;

                  password.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
                  password.setLineColor(Color.RED);
                  Toast toast=   Toast.makeText(Check_password.this, "Invalid PIN ", Toast.LENGTH_SHORT);
                  toast.show();
                  password.requestFocus();
                  Handler handler = new Handler();
                  handler.postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          toast.cancel();

                      }
                  },1000);

              }

          }
      });


    }

}