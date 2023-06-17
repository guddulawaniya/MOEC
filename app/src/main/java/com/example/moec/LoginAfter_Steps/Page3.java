package com.example.moec.LoginAfter_Steps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;

import com.example.moec.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class Page3 extends Fragment {


    String[] list = {"10th","12th","Undergraduate","Graduate","Postgraduate Certificate/Diploma","Master Degree"};


    TextInputEditText highper,interper,underper,graduateper,postper,masterper;
    TextInputLayout highperlayout,interperlayout,underperlayout,graduateperlayout,postperlayout,masterperlayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page3, container, false);

        // finding ids radio button
        RadioButton button1 = view.findViewById(R.id.radioButtonhigh);
        RadioButton button2 = view.findViewById(R.id.radioButtoninter);
        RadioButton button3 = view.findViewById(R.id.radioButtonunder);
        RadioButton button4 = view.findViewById(R.id.radioButtongraduate);
        RadioButton button5 = view.findViewById(R.id.radioButtonpost);
        RadioButton button6 = view.findViewById(R.id.radioButtonmaster);

        // finding ids textview
         highper = view.findViewById(R.id.highpercent);
         interper = view.findViewById(R.id.interpercent);
         underper = view.findViewById(R.id.undergraduatepercent);
         graduateper = view.findViewById(R.id.graduatepercent);
         postper = view.findViewById(R.id.postpercent);
         masterper = view.findViewById(R.id.masterpercent);

         // layout ids
        highperlayout = view.findViewById(R.id.highpercentlayout);
        interperlayout = view.findViewById(R.id.interpercentlayout);
        underperlayout = view.findViewById(R.id.undergraduatepercentlayout);
        graduateperlayout = view.findViewById(R.id.graduatepercentlayout);
        postperlayout = view.findViewById(R.id.postpercentlayout);
        masterperlayout = view.findViewById(R.id.masterpercentlayout);





        // clicks handling
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(3);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(4);

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(5);

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(6);

            }
        });

        return view;
    }

    void showdTextview(int id )
    {
        highperlayout.setVisibility(View.GONE);
        interperlayout.setVisibility(View.GONE);
        underperlayout.setVisibility(View.GONE);
        graduateperlayout.setVisibility(View.GONE);
        postperlayout.setVisibility(View.GONE);
        masterperlayout.setVisibility(View.GONE);

        switch (id)
        {
            case 1:  highperlayout.setVisibility(View.VISIBLE);
            break;
            case 2:  interperlayout.setVisibility(View.VISIBLE);
            break;
            case 3:  underperlayout.setVisibility(View.VISIBLE);
            break;
            case 4:  graduateperlayout.setVisibility(View.VISIBLE);
            break;
            case 5:  postperlayout.setVisibility(View.VISIBLE);
            break;
            case 6: masterperlayout.setVisibility(View.VISIBLE);
            break;
        }

    }
}