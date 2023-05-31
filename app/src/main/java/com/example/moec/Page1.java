package com.example.moec;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Page1 extends Fragment {


    String[] countrylist = {"Australia","Italy","new Zealand","Usa","Germany","Canada","Dubai","Mauritius","Poland","Latvia","Ireland","Europe","Malta"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page1, container, false);
        ListView list =  view.findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,countrylist);

        list.setAdapter(adapter);

        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getContext(), "result : "+i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "result : ", Toast.LENGTH_SHORT).show();

            }
        });

//       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//               view.setBackgroundColor(Color.parseColor("#1a519e"));
//           }
//       });
        // Inflate the layout for this fragment
        return view;
    }
}