package com.example.moec.BottomNavigation_Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.Quick_Action_Adapter;
import com.example.moec.Adapters.SliderAdapter;
import com.example.moec.Adapters.Top_country_pickup_Adapter;
import com.example.moec.Adapters.interest_area_Adapter;
import com.example.moec.Adapters.reccomended_program_Adapter;
import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.get_country_data;
import com.example.moec.JavaClass.get_subject_data;
import com.example.moec.JavaClass.getuniversitydata;
import com.example.moec.JavaClass.reccomended_programload_data;
import com.example.moec.MainActivity;
import com.example.moec.ModulesClass.Quick_Action_Module;
import com.example.moec.ModulesClass.Top_country_module;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.New_Application;
import com.example.moec.R;
import com.example.moec.onClickInterface;
import com.example.moec.program_preference_Activity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.carousel.MaskableFrameLayout;
import com.mig35.carousellayoutmanager.CarouselLayoutManager;
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.mig35.carousellayoutmanager.CenterScrollListener;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class dashboard_fragment extends Fragment {


    LinearLayout shimmereffect_Layout;
    onClickInterface onclickInterface;


    int[] images = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};
    String interesttext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_fragment, container, false);
        config();


        // auto image slider

        SliderView sliderView = view.findViewById(R.id.slider);
        SliderAdapter adapterslider = new SliderAdapter(images);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapterslider);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);

        // instances arraylist and declared arraylists

        ArrayList<module_all_program> list = new ArrayList<>();
        ArrayList<Quick_Action_Module> quickList = new ArrayList<>();

        ArrayList<module_all_program> programArrayList = new ArrayList<>();


        //Finds the recyclerview ids

        RecyclerView recyclerViewuniversity = view.findViewById(R.id.universtyRecyclerview);
        RecyclerView recommandRecyclerview = view.findViewById(R.id.recommandedRecyclerview);
        RecyclerView recyclerview_top_counryname = view.findViewById(R.id.top_country_pickups);
        RecyclerView quickRecyclerview = view.findViewById(R.id.quickRecyclerview);
        RecyclerView recyclerView = view.findViewById(R.id.interest_recyclerview);


        // and find others ids like card and textview and button

        CardView setpreference_student = view.findViewById(R.id.setpreference_hombutton);
        CardView sharefrieds = view.findViewById(R.id.refer_friends);
        TextView sharelink = view.findViewById(R.id.sharelink);
        TextView view_all = view.findViewById(R.id.view_all);
        Button saveinsterestfields = view.findViewById(R.id.saveinsterestfields);
        ImageView phoneicon = view.findViewById(R.id.phoneimage);
        LinearLayout floatingActionButton = view.findViewById(R.id.floatingActionButton2);
        sharelink.setPaintFlags(sharelink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);




        // get des_country..

        SharedPreferences preferences = getActivity().getSharedPreferences("registrationform", MODE_PRIVATE);
        String preferenceCountry = preferences.getString("pre_country", null);
        String interest = preferences.getString("interest", null);
        String education = preferences.getString("qualification", null);
        String examname = preferences.getString("examname", null);
        String userid = preferences.getString("userid", null);
        LinearLayout interestlayout = view.findViewById(R.id.interestlayout);
        if (!interest.isEmpty())
        {

            interestlayout.setVisibility(View.GONE);
        }else
        {
            interestlayout.setVisibility(View.VISIBLE);
        }

//        Toast.makeText(getContext(), "user_id : "+userid, Toast.LENGTH_SHORT).show();





        // load university data like image and name and total courses

        new getuniversitydata(getContext(), recyclerViewuniversity);


        shimmereffect_Layout = view.findViewById(R.id.dashboard_shimmer_effect_layout);
        // recommended program recyclerview and load data function..
        new reccomended_programload_data(shimmereffect_Layout, programArrayList, getContext(),
                config.Base_url + "courseApiDatawithcountry?" +
                        "countryname=" + preferenceCountry, recommandRecyclerview,setpreference_student);


        // hide and show set preference button and show recommended recyclerview





        // check out more recommended programs as your preference

        view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("fmid", 1);
                startActivity(intent);
            }
        });


        // share card on click call invite friends


        sharefrieds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendlinkwithfriends();

                } catch (Exception e) {
                    e.toString();
                }

            }
        });


        // share link and text

        sharelink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sendlinkwithfriends();

                } catch (Exception e) {
                    e.toString();
                }

            }
        });

        // vertical and cycle layout
/*        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.VERTICAL, true);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new reccomended_program_Adapter(getContext(), list));
        recyclerView.addOnScrollListener(new CenterScrollListener());*/

        // quick Recyclerview set layout and Adapter

        Quick_Action_Adapter quickAdapter = new Quick_Action_Adapter(getContext(), quickList);
        quickRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        quickRecyclerview.setAdapter(quickAdapter);
        quickRecyclerview.setNestedScrollingEnabled(false);


        phoneicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getActivity().getSharedPreferences("registrationform", MODE_PRIVATE).edit();
                editor.putString("timeline", "2");
                editor.commit();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7037282643"));
                startActivity(intent);

            }
        });


        // set preference funtion and on click function


        setpreference_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), program_preference_Activity.class));
            }
        });


        // floating button

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), New_Application.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), floatingActionButton, "fab").toBundle();
                startActivity(intent, bundle);
            }
        });


        // top countries list

        new get_country_data(shimmereffect_Layout,getContext(),recyclerview_top_counryname,config.Base_url+"crmcountriesApiData",onclickInterface,1);

        // quick access list

        quickList.add(new Quick_Action_Module(R.drawable.application, "Add \nApplication"));
        quickList.add(new Quick_Action_Module(R.drawable.ielts, "IELTS Test Booking"));
        quickList.add(new Quick_Action_Module(R.drawable.gic_icon, "GIC \nAccount"));
        quickList.add(new Quick_Action_Module(R.drawable.sop_icon, "SOP Guidance"));
        quickList.add(new Quick_Action_Module(R.drawable.accomodation, "Find Accommodation"));
        quickList.add(new Quick_Action_Module(R.drawable.loan, "Education Loan"));




        saveinsterestfields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getContext().getSharedPreferences("registrationform",MODE_PRIVATE).edit();
                editor.putString("interest",interesttext);
                editor.commit();

            }
        });


        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc, String text) {
                saveinsterestfields.setVisibility(View.VISIBLE);
                interesttext=text;

            }
        };


        new get_subject_data(shimmereffect_Layout,list,getContext(),recyclerView,config.Base_url+"crmsubjectApiData",onclickInterface,true);
        return view;
    }


    // shared element transmission

    private void config() {
        setExitSharedElementCallback(new SharedElementCallback() {});
        getActivity().getWindow().setSharedElementsUseOverlay(false);

    }


    // share with friends and earn function

    void sendlinkwithfriends() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share with Friends");
        String shareMessage = "\nLet me recommend you this application\n\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Choose on Application"));
    }


    // notification clicked function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int getid = item.getItemId();
        if (getid == R.id.notification) {
            Toast.makeText(getContext(), "Clicked Notification", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

}