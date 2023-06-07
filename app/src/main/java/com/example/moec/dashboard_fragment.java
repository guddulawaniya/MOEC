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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class dashboard_fragment extends Fragment {


    TextView student,scout,textCartItemCount;
    int id =1,mCartItemCount=100;

    ArrayList<interest_module> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_dashboard_fragment, container, false);


        SliderView sliderView = view.findViewById(R.id.slider);
        SliderAdapter adapterslider = new SliderAdapter();
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapterslider);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        ImageView phoneicon = view.findViewById(R.id.phoneimage);
        CardView profile = view.findViewById(R.id.profile);
        CardView setpreference_student = view.findViewById(R.id.setpreference_student);
        CardView addapplicationcard = view.findViewById(R.id.addapplicationcard);
        CardView testcard = view.findViewById(R.id.testcard);
        CardView sop_Guidance = view.findViewById(R.id.sop_Guidance);

        testcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), test_Activity.class));
            }
        });
        sop_Guidance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), test_Activity.class));
            }
        });

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
        addapplicationcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), New_Application.class));
            }
        });
        DrawerLayout drawerlayout = view.findViewById(R.id.drawerlayout);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                drawerlayout.openDrawer(GravityCompat.START);

            }
        });



        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton2);
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
        list.add(new interest_module(R.drawable.hospitality,"Hospitality & Tourism"));
        list.add(new interest_module(R.drawable.humanities,"Humanities & Social Science"));
        list.add(new interest_module(R.drawable.law,"Law"));
        list.add(new interest_module(R.drawable.management,"Management"));
        list.add(new interest_module(R.drawable.marketing,"Marketing & Advertising"));
        list.add(new interest_module(R.drawable.news,"Media & Journalism"));
        list.add(new interest_module(R.drawable.medical_symbol,"Medical"));
        list.add(new interest_module(R.drawable.creative_thinking,"Performing and Creative Arts"));
        list.add(new interest_module(R.drawable.science,"Science"));
        list.add(new interest_module(R.drawable.sports,"Sport & Nutrition"));
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