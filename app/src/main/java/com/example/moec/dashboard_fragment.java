package com.example.moec;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class dashboard_fragment extends Fragment {

    public dashboard_fragment() {
    }

    TextView student,scout,textCartItemCount;
    int id =1,mCartItemCount=100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_dashboard_fragment, container, false);


        ImageView refine_icon = view.findViewById(R.id.refine_icon);
        ImageView like = view.findViewById(R.id.favourate_icon_toolbar);
        TextView tooltitle = view.findViewById(R.id.toolbartitle);
        tooltitle.setText("Dashboard");
        refine_icon.setVisibility(View.GONE);
        LinearLayout notification = view.findViewById(R.id.notification);

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