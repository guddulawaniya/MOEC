package com.example.moec;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.SharedElementCallback;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.shimmer_program_Adapter;
import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.getCourse_All_dataa_API;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.Room_database.database_module;
import com.example.moec.Room_database.myAdapter;
import com.example.moec.Room_database.searchfunction_call;
import com.example.moec.Room_database.userViewModel;
import com.google.android.material.search.SearchBar;
import com.google.android.material.search.SearchView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Search_Activity extends AppCompatActivity {


    ArrayList<module_all_program> list;
    RecyclerView searchrecyclerview, simmer_efffect_layout,
            suggestionlRecyclerview;
    SearchView searchView;
    ProgressBar progressBar;
    LinearLayout nofounddata;
    private userViewModel viewModel;
    searchfunction_call call;
    Button outlinedButton;
    List<String> checkdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        config();

        TextView toolbartitle = findViewById(R.id.toolbar_title);
        TextView cleartext = findViewById(R.id.cleartext);
        TextView suggestiontextview = findViewById(R.id.suggestiontextview);
        SearchBar search_bar = findViewById(R.id.search_bar);
        ImageView backbutton = findViewById(R.id.backbutton);
//        search_button = findViewById(R.id.search_button);
//        listView = findViewById(R.id.searchrecyclerview);
        progressBar = findViewById(R.id.progressBar);
        searchrecyclerview = findViewById(R.id.searchrecyclerview);
        simmer_efffect_layout = findViewById(R.id.simmer_efffect_layout);
        suggestionlRecyclerview = findViewById(R.id.suggestionlRecyclerview);
        searchView = findViewById(R.id.searchView);
        outlinedButton = findViewById(R.id.outlinedButton);
        nofounddata = findViewById(R.id.nofounddata);
        Button updateprefernce = findViewById(R.id.nofoundbutton);
        updateprefernce.setVisibility(View.GONE);
        TextView title = findViewById(R.id.title);
        TextView descri_no_found = findViewById(R.id.descri_no_found);
        title.setText("Not Found Record");
        descri_no_found.setText("");
        searchrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        checkdata = new ArrayList<>();


        viewModel = new ViewModelProvider(this).get(userViewModel.class);

        viewModel.getLiveData().observe(this, new Observer<List<database_module>>() {
            @Override
            public void onChanged(List<database_module> users) {
                Collections.reverse(users);

                for (int i = 0; i < users.size(); i++) {
                    myAdapter adapter = new myAdapter(users, call);
                    searchrecyclerview.setAdapter(adapter);
                    suggestionlRecyclerview.setAdapter(adapter);
                }


            }
        });

        call = new searchfunction_call() {
            @Override
            public void settext(String textdata) {
                searchView.setText(textdata);
                search_bar.setText(textdata);
                filter(textdata);

            }
        };

        SearchView searchView = findViewById(R.id.searchView);

        list = new ArrayList<>();

        searchView.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() > 2) {
                    // viewModel.search_data(new database_module(charSequence.toString()));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        searchView.getEditText().setOnEditorActionListener((v, actionId, event) -> {

            list.clear();
            String query = searchView.getText().toString();
            filter(query);
            searchView.hide();
            search_bar.setText(query);
            suggestiontextview.setText("Suggestions");

            viewModel.insert(new database_module(query));

            return false;

        });


        shimmer_program_Adapter shimmereffect_adpater = new shimmer_program_Adapter();
        simmer_efffect_layout.setLayoutManager(new LinearLayoutManager(this));
        simmer_efffect_layout.setAdapter(shimmereffect_adpater);


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
        //     new getCourse_All_dataa_API(simmer_efffect_layout, list, Search_Activity.this, suggestionlRecyclerview, config.Base_url + "searchcourseprogrameApiData?search="+query,nofounddata);
        new getCourse_All_dataa_API(simmer_efffect_layout, list, Search_Activity.this, searchrecyclerview, config.Base_url + "searchcourseprogrameApiData?search=" + query, nofounddata);

    }

    private void config() {
        setExitSharedElementCallback(new SharedElementCallback() {
        });
        getWindow().setSharedElementsUseOverlay(false);
    }

}