package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class My_documents_upload extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_documents_upload);

        LinearLayout documentlieanr = findViewById(R.id.documentlinear);
        LinearLayout englishtestLinear = findViewById(R.id.englishtestLinear);
        LinearLayout exprienecLinear = findViewById(R.id.exprienecLinear);
        LinearLayout PassportLinear = findViewById(R.id.PassportLinear);
        LinearLayout OtherLinear = findViewById(R.id.OtherLinear);

        TextView clearview = findViewById(R.id.cleartext);
        TextView toolbartitle = findViewById(R.id.toolbar_title);
        ImageView backbotton = findViewById(R.id.backbutton);
        toolbartitle.setText("My Documents");
        clearview.setVisibility(View.GONE);
        backbotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        documentlieanr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbottomsheet();

            }
        });
        englishtestLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbottomsheet();

            }
        });
        exprienecLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbottomsheet();

            }
        });  PassportLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbottomsheet();

            }
        });    OtherLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openbottomsheet();

            }
        });
    }

    void openbottomsheet()
    {
        upload_document_bottom_sheet upload = new upload_document_bottom_sheet();
        upload.show(getSupportFragmentManager(),"upload data");

    }
}