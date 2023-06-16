package com.example.moec;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moec.BottomSheets.Add_bank_detail_bottomsheet;

public class bank_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);

        ImageView backbutton = findViewById(R.id.backbutton);
        ImageView no_found_image_icon = findViewById(R.id.no_found_image_icon);
        TextView cleartext = findViewById(R.id.cleartext);
        TextView toolbatitle = findViewById(R.id.toolbar_title);
        TextView title = findViewById(R.id.title);
        TextView descri_no_found = findViewById(R.id.descri_no_found);
        Button nofoundbutton = findViewById(R.id.nofoundbutton);

        nofoundbutton.setText("Add New");
        no_found_image_icon.setImageResource(R.drawable.bank_icon_24x24);
        toolbatitle.setText("Bank Details");
        cleartext.setVisibility(View.GONE);
        title.setText("No bank account added yet");
        descri_no_found.setText("Kindly add bank account details");

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        nofoundbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_bank_detail_bottomsheet bottomsheet = new Add_bank_detail_bottomsheet();
                bottomsheet.show(getSupportFragmentManager(),"bank detail");
            }
        });
    }
}