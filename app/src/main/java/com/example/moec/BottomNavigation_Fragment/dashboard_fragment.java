package com.example.moec.BottomNavigation_Fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.Quick_Action_Adapter;
import com.example.moec.Adapters.SliderAdapter;
import com.example.moec.Adapters.Top_country_pickup_Adapter;
import com.example.moec.Adapters.Univerity_Course_Adapter;
import com.example.moec.Adapters.interest_area_Adapter;
import com.example.moec.ModulesClass.Quick_Action_Module;
import com.example.moec.ModulesClass.Top_country_module;
import com.example.moec.ModulesClass.Univerity_Course_Module;
import com.example.moec.ModulesClass.interest_module;
import com.example.moec.New_Application;
import com.example.moec.R;
import com.example.moec.onClickInterface;
import com.example.moec.program_preference_Activity;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class dashboard_fragment extends Fragment {




    ArrayList<interest_module> list;
    ArrayList<Quick_Action_Module> quickList;
    ArrayList<Univerity_Course_Module> university_list;

    ArrayList<Top_country_module> topcountry_pickup_list;

    onClickInterface onclickInterface;

    int[] images = {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_dashboard_fragment, container, false);


        SliderView sliderView = view.findViewById(R.id.slider);
        SliderAdapter adapterslider = new SliderAdapter(images);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapterslider);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);


        topcountry_pickup_list = new ArrayList<>();
        quickList = new ArrayList<>();
        university_list = new ArrayList<>();
        ImageView phoneicon = view.findViewById(R.id.phoneimage);
        CardView setpreference_student = view.findViewById(R.id.setpreference_student);


        university_list.add(new Univerity_Course_Module(R.drawable.acadia_universiti_logo,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acsenda_school,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acadia_universiti_logo,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acsenda_school,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acadia_universiti_logo,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acsenda_school,"Acadia University","Course37+"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.flag_canada,"Canada"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.australia_flag,"Australia"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.uk_flag,"UK"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.us_flag,"USA"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.germany_flag,"Germany"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.zealand_flag,"New Zealand"));

        CardView sharefrieds = view.findViewById(R.id.refer_friends);
        TextView sharelink = view.findViewById(R.id.sharelink);
        sharelink.setPaintFlags(sharelink.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        sharefrieds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendlinkwithfriends();

                } catch(Exception e) {
                    e.toString();
                }

            }
        });
        sharelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendlinkwithfriends();

                } catch(Exception e) {
                    e.toString();
                }

            }
        });



        Top_country_pickup_Adapter countryAdapter = new Top_country_pickup_Adapter(getContext(),topcountry_pickup_list);



        RecyclerView recyclerview_top_counryname = view.findViewById(R.id.top_country_pickups);
        RecyclerView universtyRecyclerview = view.findViewById(R.id.universtyRecyclerview);

        universtyRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        Univerity_Course_Adapter univerity_course_adapter = new Univerity_Course_Adapter(getContext(),university_list);
        universtyRecyclerview.setAdapter(univerity_course_adapter);

        recyclerview_top_counryname.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        recyclerview_top_counryname.setAdapter(countryAdapter);

        RecyclerView quickRecyclerview = view.findViewById(R.id.quickRecyclerview);
        quickRecyclerview.setNestedScrollingEnabled(false);

        quickList.add(new Quick_Action_Module(R.drawable.application,"Add Application"));
        quickList.add(new Quick_Action_Module(R.drawable.ielts,"IELTS Test Booking"));
        quickList.add(new Quick_Action_Module(R.drawable.gic_icon,"GIC Account"));
        quickList.add(new Quick_Action_Module(R.drawable.sop_icon,"SOP Guidance"));
        quickList.add(new Quick_Action_Module(R.drawable.accomodation,"Find Accommodation"));
        quickList.add(new Quick_Action_Module(R.drawable.loan,"Education Loan"));


        Quick_Action_Adapter quickAdapter = new Quick_Action_Adapter(getContext(),quickList);

        quickRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3));
        quickRecyclerview.setAdapter(quickAdapter);





        phoneicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7037282643"));
                startActivity(intent);

            }
        });


        setpreference_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), program_preference_Activity.class));
            }
        });


        CardView floatingActionButton = view.findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), New_Application.class));
            }
        });
        list = new ArrayList<>();

        list.add(new interest_module(R.drawable.architecture,"Architecture"));
        list.add(new interest_module(R.drawable.computer,"Computer Science"));
        list.add(new interest_module(R.drawable.graphic_design,"Design"));
        list.add(new interest_module(R.drawable.engineering,"Engineering"));
        list.add(new interest_module(R.drawable.business,"Business"));
        list.add(new interest_module(R.drawable.hospitality,"Hospitality \n& Tourism"));
        list.add(new interest_module(R.drawable.humanities,"Humanities \n& Social Science"));
        list.add(new interest_module(R.drawable.law,"Law"));
        list.add(new interest_module(R.drawable.management,"Management"));
        list.add(new interest_module(R.drawable.marketing,"Marketing \n& Advertising"));
        list.add(new interest_module(R.drawable.media___communication,"Media \n& Journalism"));
        list.add(new interest_module(R.drawable.medical_symbol,"Medical"));
        list.add(new interest_module(R.drawable.creative_thinking,"Performing \n& Creative Arts"));
        list.add(new interest_module(R.drawable.science,"Science"));
        list.add(new interest_module(R.drawable.sports___fitness,"Sport \n& Nutrition"));
        list.add(new interest_module(R.drawable.translation,"Languages"));
        list.add(new interest_module(R.drawable.education,"Education"));




        RecyclerView recyclerView = view.findViewById(R.id.interest_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        interest_area_Adapter adapter = new interest_area_Adapter(list,onclickInterface);
        recyclerView.setAdapter(adapter);



        return view;
    }


    void sendlinkwithfriends()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share with Friends");
        String shareMessage= "\nLet me recommend you this application\n\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Choose on Application"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int getid = item.getItemId();
        if (getid==R.id.notification)
        {
            Toast.makeText(getContext(), "clicked notification", Toast.LENGTH_SHORT).show();
        }


        return false;
    }



}