package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.SharedElementCallback;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import android.transition.Explode;
import android.view.View;

import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.getCourse_All_dataa_API;
import com.example.moec.ModulesClass.module_all_program;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {


    ArrayList<module_all_program> list;
    RecyclerView searchrecyclerview,suggestionlRecyclerview;
    SearchView searchView;
    ProgressBar progressBar;
    LinearLayout nofounddata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        config();

        TextView toolbartitle = findViewById(R.id.toolbar_title);
        TextView cleartext = findViewById(R.id.cleartext);
        TextView suggestiontextview = findViewById(R.id.suggestiontextview);

        ImageView backbutton = findViewById(R.id.backbutton);
//        search_button = findViewById(R.id.search_button);
//        listView = findViewById(R.id.searchrecyclerview);
        progressBar = findViewById(R.id.progressBar);
        searchrecyclerview = findViewById(R.id.searchrecyclerview);
        suggestionlRecyclerview = findViewById(R.id.suggestionlRecyclerview);
        searchView = findViewById(R.id.searchView);
        nofounddata = findViewById(R.id.nofounddata);
         Button updateprefernce = findViewById(R.id.nofoundbutton);
        updateprefernce.setVisibility(View.GONE);
        TextView title = findViewById(R.id.title);
        TextView descri_no_found = findViewById(R.id.descri_no_found);
        title.setText("Not Found Record");
        descri_no_found.setText("");


        SearchView searchView = findViewById(R.id.searchView);
        SearchBar search_bar = findViewById(R.id.search_bar);
        searchView.getEditText().setOnEditorActionListener((v, actionId, event) -> {

            list.clear();

            String query = searchView.getText().toString();
            filter(query);
            search_bar.setText(query);
            suggestiontextview.setText("Result");
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


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cleartext.setVisibility(View.GONE);

        toolbartitle.setText("Search");

    }

    private void filter(String query) {
         new getCourse_All_dataa_API(progressBar, list, Search_Activity.this, suggestionlRecyclerview, config.Base_url + "searchcourseprogrameApiData?search="+query,nofounddata);
        new getCourse_All_dataa_API(progressBar, list, Search_Activity.this, searchrecyclerview, config.Base_url + "searchcourseprogrameApiData?search="+query,nofounddata);

    }

    private void config() {
        setExitSharedElementCallback(new SharedElementCallback() {
        });
       getWindow().setSharedElementsUseOverlay(false);
    }

}