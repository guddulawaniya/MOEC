package com.example.moec;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class Page3 extends Fragment {


    String[] list = {"10th","12th","Undergraduate","Graduate","Postgraduate Certificate/Diploma","Master Degree"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page3, container, false);

        RadioButton button1 = view.findViewById(R.id.radioButtonhigh);
        RadioButton button2 = view.findViewById(R.id.radioButtoninter);
        RadioButton button3 = view.findViewById(R.id.radioButtonunder);
        RadioButton button4 = view.findViewById(R.id.radioButtongraduate);
        RadioButton button5 = view.findViewById(R.id.radioButtonpost);
        RadioButton button6 = view.findViewById(R.id.radioButtonmaster);
        TextView highper = view.findViewById(R.id.highpercent);
        TextView interper = view.findViewById(R.id.interpercent);
        TextView underper = view.findViewById(R.id.undergraduatepercent);
        TextView graduateper = view.findViewById(R.id.graduatepercent);
        TextView postper = view.findViewById(R.id.postpercent);
        TextView masterper = view.findViewById(R.id.masterpercent);




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button1.isChecked())
                {
                    highper.setVisibility(View.VISIBLE);
                    interper.setVisibility(View.GONE);
                    underper.setVisibility(View.GONE);
                    graduateper.setVisibility(View.GONE);
                    postper.setVisibility(View.GONE);
                    masterper.setVisibility(View.GONE);

                }

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button2.isChecked())
                {
                    highper.setVisibility(View.GONE);
                    interper.setVisibility(View.VISIBLE);
                    underper.setVisibility(View.GONE);
                    graduateper.setVisibility(View.GONE);
                    postper.setVisibility(View.GONE);
                    masterper.setVisibility(View.GONE);

                }

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button3.isChecked())
                {
                    highper.setVisibility(View.GONE);
                    interper.setVisibility(View.GONE);
                    underper.setVisibility(View.VISIBLE);
                    graduateper.setVisibility(View.GONE);
                    postper.setVisibility(View.GONE);
                    masterper.setVisibility(View.GONE);

                }

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button4.isChecked())
                {
                    highper.setVisibility(View.GONE);
                    interper.setVisibility(View.GONE);
                    underper.setVisibility(View.GONE);
                    graduateper.setVisibility(View.VISIBLE);
                    postper.setVisibility(View.GONE);
                    masterper.setVisibility(View.GONE);

                }

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button5.isChecked())
                {
                    highper.setVisibility(View.GONE);
                    interper.setVisibility(View.GONE);
                    underper.setVisibility(View.GONE);
                    graduateper.setVisibility(View.GONE);
                    postper.setVisibility(View.VISIBLE);
                    masterper.setVisibility(View.GONE);

                }

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button6.isChecked())
                {
                    highper.setVisibility(View.GONE);
                    interper.setVisibility(View.GONE);
                    underper.setVisibility(View.GONE);
                    graduateper.setVisibility(View.GONE);
                    postper.setVisibility(View.GONE);
                    masterper.setVisibility(View.VISIBLE);
                }

            }
        });

        return view;
    }
}