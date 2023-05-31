package com.example.moec;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Page5 extends Fragment {

    TextView reading,writing,listening,speaking,overall,total;

    TextInputEditText readinginputtext, writinginput,listeninginput,speakinginput,overallinput,totalinput,percent1,percent2;
    TextInputLayout readinglayout, writinglayout,listeninglayout,speakinglayout,overalllayout,totallayout,percentlayout1,percentlayout2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String[] list = {"I don't have this","I will appear soon","IELTS","PTE","TOEFL","Duoling English Test","GRE","GMAT"};
        View view = inflater.inflate(R.layout.fragment_page5, container, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,list);

        AutoCompleteTextView selectexam = view.findViewById(R.id.selectexam_dropdown);
        LinearLayout linear2 = view.findViewById(R.id.secoundlinear);
        LinearLayout linea1 = view.findViewById(R.id.linear1);
        LinearLayout linea3 = view.findViewById(R.id.linear3);
        reading = view.findViewById(R.id.readingtext);
        writing = view.findViewById(R.id.writingtext);
        listening = view.findViewById(R.id.listeningtext);
        speaking = view.findViewById(R.id.speakingtext);
        overall = view.findViewById(R.id.overalltext);
        readinglayout = view.findViewById(R.id.readinginputfieldslayout);
        writinglayout = view.findViewById(R.id.writinginputlayout);
        listeninglayout = view.findViewById(R.id.listeninglayout);
        speakinglayout = view.findViewById(R.id.speakinginputlayout);
        overalllayout = view.findViewById(R.id.overallinputlayout);

        TextView examtitle = view.findViewById(R.id.examtitle);


        TextInputLayout overall = view.findViewById(R.id.readinginputfieldslayout);



        selectexam.setAdapter(adapter);

        selectexam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                examtitle.setText("Enter "+list[i]+"Score");
                if (i<2)
                {
                    examtitle.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linea3.setVisibility(View.GONE);
                    linea1.setVisibility(View.GONE);
                }
                else if (i>1 && i<5) {

                    examtitle.setVisibility(View.VISIBLE);
                    linear2.setVisibility(View.GONE);
                    linea3.setVisibility(View.GONE);
                    linea1.setVisibility(View.VISIBLE);

                }

                else if (i>5)
                {


                    examtitle.setVisibility(View.VISIBLE);
                    linea1.setVisibility(View.GONE);
                    linea3.setVisibility(View.GONE);
                    linear2.setVisibility(View.VISIBLE);

                }
                else if (i==5)
                {
                    examtitle.setVisibility(View.VISIBLE);

                    linea1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linea3.setVisibility(View.VISIBLE);

                }

            }
        });
        return view;






    }
}