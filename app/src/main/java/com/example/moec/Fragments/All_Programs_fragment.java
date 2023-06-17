package com.example.moec.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;

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
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
        list.add(new module_all_program("Geography BA (Hons) with Pacement",duration+"Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));

        All_program_Adapter adapter = new All_program_Adapter(list,getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }
}