package com.example.moec.BottomNavigation_Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.Adapters.Quick_Action_Adapter;
import com.example.moec.Adapters.SliderAdapter;
import com.example.moec.Adapters.Top_country_pickup_Adapter;
import com.example.moec.Adapters.Univerity_Course_Adapter;
import com.example.moec.Adapters.interest_area_Adapter;
import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.getuniversitydata;
import com.example.moec.JavaClass.getuniversitydataAPI;
import com.example.moec.MainActivity;
import com.example.moec.ModulesClass.Quick_Action_Module;
import com.example.moec.ModulesClass.Top_country_module;
import com.example.moec.ModulesClass.Univerity_Course_Module;
import com.example.moec.ModulesClass.interest_module;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.New_Application;
import com.example.moec.R;
import com.example.moec.loginActivity.login_Activity;
import com.example.moec.onClickInterface;
import com.example.moec.program_preference_Activity;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import kotlin.text.Charsets;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class dashboard_fragment extends Fragment {




    ProgressBar progressBar;
    onClickInterface onclickInterface;


    int[] images = {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_fragment, container, false);
        config();

        //loader id find

        progressBar = view.findViewById(R.id.progressBar);


        // auto image slider

        SliderView sliderView = view.findViewById(R.id.slider);
        SliderAdapter adapterslider = new SliderAdapter(images);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapterslider);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);


        // instances arraylist and declared arraylists

        ArrayList<interest_module> list = new ArrayList<>();
        ArrayList<Quick_Action_Module> quickList=new ArrayList<>();
        ArrayList<Top_country_module> topcountry_pickup_list= new ArrayList<>();

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

        String registrationURL = config.Base_url + "setPreferenceApiData?" +
                "user_id=" +userid+
                "&des_country="  +
                "&intrest="  +
                "&qualification="  +
                "&edu_marsks="  +
                "&englishtest="  +
                "&writingscore="  +
                "&listeningscore="  +
                "&readingscore="  +
                "&speakingscore="  +
                "&over_allscore=" ;


        // load university data like image and name and total courses

        new getuniversitydata(getContext(),recyclerViewuniversity);


        // recommended program recyclerview and load data function..

        new getuniversitydataAPI(progressBar, programArrayList, getContext(), recommandRecyclerview, config.Base_url +"courseApiDatawithcountry?"+"countryname="+preferenceCountry,  true);





        // hide and show set preference button and show recommended recyclerview


        if (preferenceCountry != null && interest != null && education != null && examname != null) {
            
            setpreference_student.setVisibility(View.GONE);
            recommandRecyclerview.setVisibility(View.VISIBLE);

        } else {
            setpreference_student.setVisibility(View.VISIBLE);
            recommandRecyclerview.setVisibility(View.GONE);
        }




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





        // Top countries Adapter and layout set

        Top_country_pickup_Adapter countryAdapter = new Top_country_pickup_Adapter(getContext(), topcountry_pickup_list);
        recyclerview_top_counryname.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerview_top_counryname.setAdapter(countryAdapter);





        // quick Recyclerview set layout and Adapter

        Quick_Action_Adapter quickAdapter = new Quick_Action_Adapter(getContext(), quickList);
        quickRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 3));
        quickRecyclerview.setAdapter(quickAdapter);
        quickRecyclerview.setNestedScrollingEnabled(false);




        phoneicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getActivity().getSharedPreferences("registrationform", MODE_PRIVATE).edit();
                editor.putInt("timeline", 2);
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

        topcountry_pickup_list.add(new Top_country_module(R.drawable.flag_canada, "Canada"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.australia_flag, "Australia"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.uk_flag, "UK"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.us_flag, "USA"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.germany_flag, "Germany"));
        topcountry_pickup_list.add(new Top_country_module(R.drawable.zealand_flag, "New Zealand"));


        // quick access list

        quickList.add(new Quick_Action_Module(R.drawable.application, "Add \nApplication"));
        quickList.add(new Quick_Action_Module(R.drawable.ielts, "IELTS Test Booking"));
        quickList.add(new Quick_Action_Module(R.drawable.gic_icon, "GIC \nAccount"));
        quickList.add(new Quick_Action_Module(R.drawable.sop_icon, "SOP Guidance"));
        quickList.add(new Quick_Action_Module(R.drawable.accomodation, "Find Accommodation"));
        quickList.add(new Quick_Action_Module(R.drawable.loan, "Education Loan"));

        // subjects list

        list.add(new interest_module(R.drawable.architecture, "Architecture"));
        list.add(new interest_module(R.drawable.computer, "Computer Science"));
        list.add(new interest_module(R.drawable.graphic_design, "Design"));
        list.add(new interest_module(R.drawable.engineering, "Engineering"));
        list.add(new interest_module(R.drawable.business, "Business"));
        list.add(new interest_module(R.drawable.hospitality, "Hospitality \n& Tourism"));
        list.add(new interest_module(R.drawable.humanities, "Humanities \n& Social Science"));
        list.add(new interest_module(R.drawable.law, "Law"));
        list.add(new interest_module(R.drawable.management, "Management"));
        list.add(new interest_module(R.drawable.marketing, "Marketing \n& Advertising"));
        list.add(new interest_module(R.drawable.media___communication, "Media \n& Journalism"));
        list.add(new interest_module(R.drawable.medical_symbol, "Medical"));
        list.add(new interest_module(R.drawable.creative_thinking, "Performing \n& Creative Arts"));
        list.add(new interest_module(R.drawable.science, "Science"));
        list.add(new interest_module(R.drawable.sports___fitness, "Sport \n& Nutrition"));
        list.add(new interest_module(R.drawable.translation, "Languages"));
        list.add(new interest_module(R.drawable.education, "Education"));

        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int abc, String text) {

            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        interest_area_Adapter adapter = new interest_area_Adapter(list, onclickInterface);
        recyclerView.setAdapter(adapter);

        return view;
    }




    // shared element transmission

    private void config() {
        setExitSharedElementCallback(new SharedElementCallback(){});
        getActivity().getWindow().setSharedElementsUseOverlay(false);
    }



    // share with friends and earn function

    void sendlinkwithfriends()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share with Friends");
        String shareMessage= "\nLet me recommend you this application\n\n";
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Choose on Application"));
    }



    // notification clicked function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int getid = item.getItemId();
        if (getid==R.id.notification)
        {
            Toast.makeText(getContext(), "Clicked Notification", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

}