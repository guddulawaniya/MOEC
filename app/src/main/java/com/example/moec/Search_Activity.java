package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.os.Bundle;

import android.transition.Explode;
import android.view.View;

import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.search.SearchView;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {

    ListView listView, listView1;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    CardView search_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView toolbartitle = findViewById(R.id.toolbar_title);
        TextView cleartext = findViewById(R.id.cleartext);

        ImageView backbutton = findViewById(R.id.backbutton);
//        search_button = findViewById(R.id.search_button);
        listView = findViewById(R.id.listView);
        listView1 = findViewById(R.id.listView1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


        SearchView searchView = findViewById(R.id.searchView);
        searchView.getEditText().setOnEditorActionListener((v, actionId, event) -> {

            Toast.makeText(this, "search : " + searchView.getText(), Toast.LENGTH_SHORT).show();

            searchView.hide();
            return false;
        });


//        searchfieldtext.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                if(list.contains(query)){
//                    adapter.getFilter().filter(query);
//
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });

    /*    search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
*/

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

        adapter = new ArrayAdapter<>(this, R.layout.search_card, list);
        listView.setAdapter(adapter);
        listView1.setAdapter(adapter);


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cleartext.setVisibility(View.GONE);

        toolbartitle.setText("Search");

    }



}