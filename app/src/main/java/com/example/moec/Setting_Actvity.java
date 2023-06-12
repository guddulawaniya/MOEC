package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Setting_Actvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_actvity);
        getSupportActionBar().hide();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are leaving your current session to log in to your new account. Can we proceed?");


        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {

           startActivity(new Intent(Setting_Actvity.this, login_Activity.class));
           finish();
        });


        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();

        TextView cleartext = findViewById(R.id.cleartext);
        TextView title  = findViewById(R.id.toolbar_title);
        TextView contactus  = findViewById(R.id.contactus);
        TextView terms_use  = findViewById(R.id.term_use);
        TextView login_new_page  = findViewById(R.id.login_new_account);
        TextView change_password  = findViewById(R.id.changepasscode);
        ImageView backbutton = findViewById(R.id.backbutton);

        terms_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), webviewActivity.class);
                intent.putExtra("id",true);
                startActivity(intent);

            }
        });
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), webviewActivity.class);
                intent.putExtra("id",false);
                startActivity(intent);

            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        login_new_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), change_passcode_Actvity.class));


            }
        });

        title.setText("Settings");
        cleartext.setVisibility(View.GONE);


    }
}