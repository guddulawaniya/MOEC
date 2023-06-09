package com.example.moec;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class Page3 extends Fragment {


    String[] list = {"10th","12th","Undergraduate","Graduate","Postgraduate Certificate/Diploma","Master Degree"};


    TextView highper,interper,underper,graduateper,postper,masterper;

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
        highper.setVisibility(View.GONE);
        interper.setVisibility(View.GONE);
        underper.setVisibility(View.GONE);
        graduateper.setVisibility(View.GONE);
        postper.setVisibility(View.GONE);
        masterper.setVisibility(View.GONE);

        switch (id)
        {
            case 1:  highper.setVisibility(View.VISIBLE);
            break;
            case 2:  interper.setVisibility(View.VISIBLE);
            break;
            case 3:  underper.setVisibility(View.VISIBLE);
            break;
            case 4:  graduateper.setVisibility(View.VISIBLE);
            break;
            case 5:  postper.setVisibility(View.VISIBLE);
            break;
            case 6: masterper.setVisibility(View.VISIBLE);
            break;
        }

    }
}