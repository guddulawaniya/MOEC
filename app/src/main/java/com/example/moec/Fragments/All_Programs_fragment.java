package com.example.moec.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.MainActivity;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;
import com.example.moec.onClickInterface;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class All_Programs_fragment extends Fragment {


    ArrayList<module_all_program> list;
    int duration=48;
    int fees=48000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all__programs_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.courses_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","University of Worcester, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Birmingham City University, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","De Montfort University, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Middlesex University London, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Canterbury Christ Church University, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Keele University, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","University for the Creative Arts, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","University of Liverpool, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Amity University, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Abertay University, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"Canada","University Canada West"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","University of East London, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Oxford Brookes University, UK"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"Canada","Cape Breton University"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Queen's University Belfast, UK"));

        All_program_Adapter adapter = new All_program_Adapter(list,getContext(),1);
        recyclerView.setAdapter(adapter);

        return view;
    }

}