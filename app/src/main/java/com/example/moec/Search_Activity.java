package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.widget.AdapterView;
import android.widget.SearchView;


import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {

    ListView listView;

    SearchView searchfieldtext;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    CardView search_button ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        config();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView toolbartitle = findViewById(R.id.toolbar_title);
        searchfieldtext = (SearchView) findViewById(R.id.searchView);
        TextView cleartext = findViewById(R.id.cleartext);
        ImageView backbutton = findViewById(R.id.backbutton);
//        search_button = findViewById(R.id.search_button);
        listView = findViewById(R.id.listView);


        searchfieldtext.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

//        search_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });




        list = new ArrayList<>();
        list.add("Middlesex University London, UK");
        list.add("University of East London, UK");
        list.add("De Montfort University, UK");
        list.add("Canterbury Christ Church University, UK");
        list.add("Keele University, UK");
        list.add("Birmingham City University, UK");
        list.add("University for the Creative Arts, UK");
        list.add("University of Liverpool, UK");
        list.add("Canterbury Christ Church University, UK");
        list.add("Keele University, UK");

        adapter = new ArrayAdapter<String>(this, R.layout.search_card,list);
        listView.setAdapter(adapter);







        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              onBackPressed();
            }
        });

        cleartext.setVisibility(View.GONE);

        toolbartitle.setText("Search");

    }

    private void config() {
        findViewById(android.R.id.content).setTransitionName("search");

        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.addTarget(android.R.id.content);
        transform.setDuration(500);

        getWindow().setSharedElementEnterTransition(transform);
        getWindow().setSharedElementReturnTransition(transform);



    }


}