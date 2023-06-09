package com.example.moec;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class dashboard_fragment extends Fragment {


    TextView student,scout,textCartItemCount;
    int id =1,mCartItemCount=100;

    ArrayList<interest_module> list;
    ArrayList<Quick_Action_Module> quickList;
    ArrayList<Univerity_Course_Module> university_list;

    ArrayList<Top_country_module> topcountry_pickup_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_dashboard_fragment, container, false);

        TextView uploaddata = view.findViewById(R.id.sidedocument_upload);
        uploaddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), My_documents_upload.class);
                startActivity(intent);
            }
        });

        SliderView sliderView = view.findViewById(R.id.slider);
        SliderAdapter adapterslider = new SliderAdapter();
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapterslider);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        topcountry_pickup_list = new ArrayList<>();
        quickList = new ArrayList<>();
        university_list = new ArrayList<>();
        ImageView phoneicon = view.findViewById(R.id.phoneimage);
        CardView profile = view.findViewById(R.id.profile);
        CardView setpreference_student = view.findViewById(R.id.setpreference_student);


        university_list.add(new Univerity_Course_Module(R.drawable.acadia_universiti_logo,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acsenda_school,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acadia_universiti_logo,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acsenda_school,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acadia_universiti_logo,"Acadia University","Course37+"));
        university_list.add(new Univerity_Course_Module(R.drawable.acsenda_school,"Acadia University","Course37+"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.flag_canada,"Canada"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.australia_flag,"Australia"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.flag_canada,"Canada"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.australia_flag,"Australia"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.flag_canada,"Canada"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.australia_flag,"Australia"));




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
        DrawerLayout drawerlayout = view.findViewById(R.id.drawerlayout);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                drawerlayout.openDrawer(GravityCompat.START);

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
        list.add(new interest_module(R.drawable.news,"Media \n& Journalism"));
        list.add(new interest_module(R.drawable.medical_symbol,"Medical"));
        list.add(new interest_module(R.drawable.creative_thinking,"Performing \n& Creative Arts"));
        list.add(new interest_module(R.drawable.science,"Science"));
        list.add(new interest_module(R.drawable.sports,"Sport \n& Nutrition"));
        list.add(new interest_module(R.drawable.translation,"Languages"));
        list.add(new interest_module(R.drawable.education,"Education"));

        ImageView refine_icon = view.findViewById(R.id.refine_icon);
        ImageView like = view.findViewById(R.id.favourate_icon_toolbar);
        TextView tooltitle = view.findViewById(R.id.toolbartitle);
        tooltitle.setText("Dashboard");
        refine_icon.setVisibility(View.GONE);
        LinearLayout notification = view.findViewById(R.id.notification);


        RecyclerView recyclerView = view.findViewById(R.id.interest_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        interest_area_Adapter adapter = new interest_area_Adapter(list);
        recyclerView.setAdapter(adapter);


        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Favorate_Activity.class));

            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Notification_Activity.class));
            }
        });



        textCartItemCount = view.findViewById(R.id.notification_badge);
        TextView view_all  = view.findViewById(R.id.view_all);
        TextView application  = view.findViewById(R.id.application);
        TextView sidebarPreferenece  = view.findViewById(R.id.sidebarPreferenece);
        TextView profiledetails  = view.findViewById(R.id.profiledetails);
        sidebarPreferenece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), program_preference_Activity.class));
            }
        });
        application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), New_Application.class));
            }
        });
        profiledetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), profile_dashboard.class));
            }
        });





//        replaceFragment(id);
//        student.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (id==0)
//                {
//                    id++;
//                    replaceFragment(id);
//                }
//
//
//            }
//        });
//        scout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (id==1)
//                {
//                    id--;
//                    replaceFragment(id);
//                }
//
//
//
//            }
//        });

        return view;
    }


//    void changecolor(int id)
//    {
//        if (id==1)
//        {
//            student.setBackground(getResources().getDrawable(R.drawable.selectedtablayoutbg));
//            student.setTextColor(getResources().getColor(R.color.white));
//            scout.setBackground(getResources().getDrawable(R.color.background_blue_shadew));
//            scout.setTextColor(getResources().getColor(R.color.text_color));
//        }
//        else {
//            student.setBackground(getResources().getDrawable(R.color.background_blue_shadew));
//            student.setTextColor(getResources().getColor(R.color.text_color));
//
//            scout.setBackground(getResources().getDrawable(R.drawable.selectedtablayoutbg));
//            scout.setTextColor(getResources().getColor(R.color.white));
//        }
//
//    }

//    void replaceFragment(int id)
//    {
//        scout_Fragment scout_fragment = new scout_Fragment();
//        student_Fragment student_fragment = new student_Fragment();
//        FragmentManager manager = getFragmentManager();
//        androidx.fragment.app.FragmentTransaction fragmentTransaction = manager.beginTransaction();
//       if (id==1)
//       {
//           changecolor(1);
//           fragmentTransaction.replace(R.id.mainframelayout,student_fragment);
//
//
//       }
//       else
//       {
//           changecolor(id);
//           fragmentTransaction.replace(R.id.mainframelayout,scout_fragment);
//
//       }
//        fragmentTransaction.commit();
//
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int getid = item.getItemId();
        if (getid==R.id.notification)
        {
            Toast.makeText(getContext(), "clicked notification", Toast.LENGTH_SHORT).show();
        }


        return false;
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}