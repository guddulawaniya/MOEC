package com.example.moec;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Page1 extends Fragment {


    ArrayList<listmodule> list;
    String[] countrylist = {"Australia","Italy","new Zealand","Usa","Germany","Canada","Dubai","Mauritius","Poland","Latvia","Ireland","Europe","Malta"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page1, container, false);
        RecyclerView recyclerView =  view.findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        list.add(new listmodule("Australia"));
        list.add(new listmodule("Italy"));
        list.add(new listmodule("new Zealand"));
        list.add(new listmodule("Usa"));
        list.add(new listmodule("Germany"));
        list.add(new listmodule("Canada"));
        list.add(new listmodule("Dubai"));
        list.add(new listmodule("Poland"));
        list.add(new listmodule("Latvia"));
        list.add(new listmodule("Ireland"));
        list.add(new listmodule("Europe"));
        list.add(new listmodule("Malta"));

      list_adapter list_adapter = new list_adapter(list,getContext());
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      recyclerView.setAdapter(list_adapter);




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