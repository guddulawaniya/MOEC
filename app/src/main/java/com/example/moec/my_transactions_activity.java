package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class my_transactions_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_transactions);
        getSupportActionBar().hide();
    }
}