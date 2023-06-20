package com.example.moec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chaos.view.PinView;
import com.example.moec.Adapters.interest_area_Adapter;
import com.example.moec.Adapters.most_prefered_destination_Adapter;
import com.example.moec.ModulesClass.interest_module;
import com.example.moec.ModulesClass.most_prefered_destination_module;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class preference_Actvity extends AppCompatActivity {

    TextView index;
    StepView stepView;
    int stepcount = 1;
    int id;
    Button nextbutton;
    LinearLayout page1, page2, page3, page4, page5, page6,ptelinear,ieltslinear,toefllinear,linearduoling,donotLinear;
    TextInputLayout highperlayout, interperlayout, underperlayout, graduateperlayout, postperlayout, masterperlayout;
    TextInputEditText highper, interper, underper, graduateper, postper, masterper;
    RadioButton button1, button2, button3, button4, button5, button6, interradio, graduateradio, postradio, masterradio;
    RecyclerView mostpreferedRecyclerview;
    ArrayList<most_prefered_destination_module> mostpreferedlist;
    PinView setpassword,comfirmpassword;

    CheckBox passcheck ;
    boolean checkselectcountry= false;
    TextView notmatchedtext;
    onClickInterface onclickInterface;

    String[] list = {"I don't have this","I will appear soon","IELTS","PTE","TOEFL","Duoling English Test","GRE","GMAT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_actvity);


        nextbutton = findViewById(R.id.nextbutton);
        setpassword=findViewById(R.id.setpassword);
        comfirmpassword=findViewById(R.id.comfirmpassword);
        passcheck = findViewById(R.id.passcheck);

        // layout ids
        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page3 = findViewById(R.id.page3);
        page4 = findViewById(R.id.page4);
        page5 = findViewById(R.id.page5);
        page6 = findViewById(R.id.page6);
         notmatchedtext = findViewById(R.id.notmatchedtext);


        // page 4 i want to study in aboard radio button
        interradio = findViewById(R.id.radioButtoninterpage4);
        graduateradio = findViewById(R.id.radioButtongraduatepage4);
        postradio = findViewById(R.id.radioButtonpostpage4);
        masterradio = findViewById(R.id.radioButtonmasterpage4);

        page1dataset();
        page2dataset();
        page5setdata();
        page6setdata();

        // finding ids textview
        highper = findViewById(R.id.highpercent);
        interper = findViewById(R.id.interpercent);
        underper = findViewById(R.id.undergraduatepercent);
        graduateper = findViewById(R.id.graduatepercent);
        postper = findViewById(R.id.postpercent);
        masterper = findViewById(R.id.masterpercent);
        highperlayout = findViewById(R.id.highpercentlayout);
        interperlayout = findViewById(R.id.interpercentlayout);
        underperlayout = findViewById(R.id.undergraduatepercentlayout);
        graduateperlayout = findViewById(R.id.graduatepercentlayout);
        postperlayout = findViewById(R.id.postpercentlayout);
        masterperlayout = findViewById(R.id.masterpercentlayout);




        reading = findViewById(R.id.readinginput);
        writing = findViewById(R.id.writinginput);
        listening = findViewById(R.id.listeninginput);
        speaking = findViewById(R.id.speakinginput);
        overall = findViewById(R.id.overallinput);
        readinglayout = findViewById(R.id.RLayout);
        writinglayout = findViewById(R.id.wlayout);
        listeninglayout = findViewById(R.id.listeninglayout);
        speakinglayout = findViewById(R.id.slayout);
        overalllayout = findViewById(R.id.Overlayout);
        Doverall = findViewById(R.id.Doverallinput);
        Doveralllayout = findViewById(R.id.DoverallLayout);

        button1 = findViewById(R.id.radioButtonhigh);
        button2 = findViewById(R.id.radioButtoninter);
        button3 = findViewById(R.id.radioButtonunder);
        button4 = findViewById(R.id.radioButtongraduate);
        button5 = findViewById(R.id.radioButtonpost);
        button6 = findViewById(R.id.radioButtonmaster);
        testinputTextWatcher();



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(3);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(4);

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(5);

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdTextview(6);

            }
        });


        setpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                notmatchedtext.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        comfirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                notmatchedtext.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // mainActivity Code

        index = findViewById(R.id.indexingtext);
        stepView = findViewById(R.id.step_view);
        progressbar(stepcount);

        ImageView backbutton = findViewById(R.id.backbutton);


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (page1.getVisibility() == View.VISIBLE) {

                    if (checkselectcountry)
                    {
                        checkselectcountry = false;
                        stepcount++;
                        progressbar(stepcount);
                    }
                    else {
                        Toast.makeText(preference_Actvity.this, "Please Select Country", Toast.LENGTH_SHORT).show();
                    }

                } else if (page2.getVisibility() == View.VISIBLE) {

                    if (checkselectcountry)
                    {
                        checkselectcountry = false;
                        stepcount++;
                        progressbar(stepcount);
                    }
                    else {

                        Toast.makeText(preference_Actvity.this, "Please Select Interest", Toast.LENGTH_SHORT).show();
                    }


                } else if (page3.getVisibility() == View.VISIBLE) {
                    progressbar(stepcount);
                    page3dataset();

                } else if (page4.getVisibility() == View.VISIBLE) {
                    page4selectstudy();

                } else if (page5.getVisibility() == View.VISIBLE) {


                    if (ieltsinputlayout.getVisibility()==View.VISIBLE || pteinputlayout.getVisibility()==View.VISIBLE ||
                            duolinggoinputlayout.getVisibility()==View.VISIBLE|| toeflinputlayout.getVisibility()==View.VISIBLE)
                    {
                       if (ieltsinputlayout.getVisibility()==View.VISIBLE)
                       {
                           validationPage5(ieltsinputlayout);

                       }else if (pteinputlayout.getVisibility()==View.VISIBLE)
                       {
                           validationPage5(pteinputlayout);

                       }
                       else if (toeflinputlayout.getVisibility()==View.VISIBLE)
                       {
                           validationPage5(toeflinputlayout);

                       }
                       else if (duolinggoinputlayout.getVisibility()==View.VISIBLE)
                       {
                           validationPage5(duolinggoinputlayout);

                       }
                    }
                    else {

                        Toast.makeText(preference_Actvity.this, "Please Select Course", Toast.LENGTH_SHORT).show();
                    }





                } else if (page6.getVisibility() == View.VISIBLE) {

                    if (!setpassword.getText().toString().isEmpty() && !comfirmpassword.getText().toString().isEmpty())
                    {
                        if (setpassword.getText().toString().equals(comfirmpassword.getText().toString()))
                        {
                            if (passcheck.isChecked())
                            {
                                stepcount++;
                                progressbar(stepcount);
                            }
                            else
                            {
                                passcheck.setError("Please Checked Box");
                                Toast.makeText(preference_Actvity.this, "Please Checked Box", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                            notmatchedtext.setVisibility(View.VISIBLE);
                        notmatchedtext.setText("Password not matched");

                    }
                    else
                    {
                        notmatchedtext.setVisibility(View.VISIBLE);
                        notmatchedtext.setText("Please set password Future Login");
                    }



                }


            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }



    void progressbar(int stepcount) {
        index.setText(stepcount + " of 7 ");
        stepView.getState()
                .animationType(StepView.ANIMATION_LINE)
                .nextStepLineColor(ContextCompat.getColor(getApplicationContext(), R.color.background_blue_shadew))
                .doneStepMarkColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .stepsNumber(7)
                .nextStepCircleEnabled(false)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(6)
                .commit();
        stepView.go(stepcount, true);

        changelayout(stepcount);


    }


    void changelayout(int id) {
        switch (id) {
            case 1:
                page1.setVisibility(View.VISIBLE);
                page2.setVisibility(View.GONE);
                page3.setVisibility(View.GONE);
                page4.setVisibility(View.GONE);
                page5.setVisibility(View.GONE);
                page6.setVisibility(View.GONE);

                break;
            case 2:
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
                page3.setVisibility(View.GONE);
                page4.setVisibility(View.GONE);
                page5.setVisibility(View.GONE);
                page6.setVisibility(View.GONE);
                break;
            case 3:
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.GONE);
                page3.setVisibility(View.VISIBLE);
                page4.setVisibility(View.GONE);
                page5.setVisibility(View.GONE);
                page6.setVisibility(View.GONE);
                break;
            case 4:
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.GONE);
                page3.setVisibility(View.GONE);
                page4.setVisibility(View.VISIBLE);
                page5.setVisibility(View.GONE);
                page6.setVisibility(View.GONE);
                break;
            case 5:
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.GONE);
                page3.setVisibility(View.GONE);
                page4.setVisibility(View.GONE);
                page5.setVisibility(View.VISIBLE);
                page6.setVisibility(View.GONE);
                break;
            case 6:
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.GONE);
                page3.setVisibility(View.GONE);
                page4.setVisibility(View.GONE);
                page5.setVisibility(View.GONE);
                page6.setVisibility(View.VISIBLE);

                break;
            case 7:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
        }

    }


    void saveradiobuttondatapage3(RadioButton text, TextInputEditText percentage,TextInputLayout layout) {

        String radiotext = text.getText().toString();
        String percentageText  = percentage.getText().toString();

        if (!percentageText.isEmpty()) {

            SharedPreferences.Editor editor = getSharedPreferences("qualification", Context.MODE_PRIVATE).edit();
            editor.putString("percentage", percentageText);
            editor.putString("educationtext", radiotext);
            editor.commit();
            stepcount++;
            progressbar(stepcount);



        }
        else {

            layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
            layout.setError("Required*");
            percentage.requestFocus();
        }


        percentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                layout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });







    }

    @Override
    public void onBackPressed() {
        stepcount--;

        if (stepcount == 0) {
            super.onBackPressed();
        } else if (stepcount < 8 && stepcount > 0) {

            progressbar(stepcount);

        }


    }


    // layout setdata


    void page1dataset() {
        mostpreferedlist = new ArrayList<>();
        mostpreferedRecyclerview = findViewById(R.id.mostpreferedRecyclerview);
        mostpreferedRecyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int position) {

                if (position>-1)
                {
                    SharedPreferences.Editor editor = getSharedPreferences("qualification", Context.MODE_PRIVATE).edit();
                    editor.putString("countryname", String.valueOf(mostpreferedlist.get(position)));
                    editor.commit();
                    checkselectcountry=true;

                }

            }
        };

        // nested scolling set false
        mostpreferedRecyclerview.setNestedScrollingEnabled(false);

        // object Adapters
        most_prefered_destination_Adapter mostAdapter = new most_prefered_destination_Adapter(mostpreferedlist, getApplicationContext(),onclickInterface);

        // set adapter on recycler view
        mostpreferedRecyclerview.setAdapter(mostAdapter);



        // add data on list
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.uk_flag, "UK"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.us_flag, "USA"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.flag_canada, "Canada"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.australia_flag, "Australia"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.flag_canada, "Italy"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.germany_flag, "Germany"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.zealand_flag, "New Zealand"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.dubai_flag, "Dubai"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.poland_flag, "Poland"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.ireland_flag, "Ireland"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.latvia_flag, "Latvia"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.mauritius_flg, "Mauritius"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.malta_flag, "Malta"));

    }


    void page2dataset() {
        // finding ids

        RecyclerView recyclerView = findViewById(R.id.interest_recyclerview);


        // instance of array list
        ArrayList<interest_module> list = new ArrayList<>();

        onclickInterface = new onClickInterface() {
            @Override
            public void setClick(int position) {
                if (!checkselectcountry)
                {
                    SharedPreferences.Editor editor = getSharedPreferences("qualification", Context.MODE_PRIVATE).edit();
                    editor.putString("interestarea", String.valueOf(mostpreferedlist.get(position)));
                    editor.commit();
                    checkselectcountry = true;
                }

            }
        };

        // object of interest Adapter
        interest_area_Adapter adapter = new interest_area_Adapter(list,onclickInterface);


        // set layout manager on recyclerview
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        recyclerView.setAdapter(adapter);

        // add data on list

        list.add(new interest_module(R.drawable.architecture, "Architecture"));
        list.add(new interest_module(R.drawable.art___design, "Design"));
        list.add(new interest_module(R.drawable.engineering, "Engineering"));
        list.add(new interest_module(R.drawable.business___management, "Business"));
        list.add(new interest_module(R.drawable.hospitality, "Hospitality & Tourism"));
        list.add(new interest_module(R.drawable.humanities, "Humanities & Social Science"));
        list.add(new interest_module(R.drawable.law___legal_studies, "Law"));
        list.add(new interest_module(R.drawable.marketing, "Marketing & Advertising"));
        list.add(new interest_module(R.drawable.media___communication, "Media & Journalism"));
        list.add(new interest_module(R.drawable.health___nursing, "Medical"));
        list.add(new interest_module(R.drawable.science, "Science"));
        list.add(new interest_module(R.drawable.sports___fitness, "Sport & Nutrition"));
        list.add(new interest_module(R.drawable.education, "Education"));
    }


    void page3dataset() {
        if (button1.isChecked() || button2.isChecked() ||
                button3.isChecked() || button4.isChecked() || button5.isChecked() || button6.isChecked())
        {
            if (button1.isChecked()) {


                saveradiobuttondatapage3(button1, highper, highperlayout);
                page4setdata(1);
            }
            if (button2.isChecked()) {
                saveradiobuttondatapage3(button2, interper, interperlayout);
                page4setdata(2);
            }
            if (button3.isChecked()) {

                page4setdata(3);
                saveradiobuttondatapage3(button3, underper, underperlayout);
            }
            if (button4.isChecked()) {
                page4setdata(3);
                saveradiobuttondatapage3(button4, graduateper, graduateperlayout);
            }
            if (button5.isChecked()) {
                saveradiobuttondatapage3(button5, postper, postperlayout);

            }
            if (button6.isChecked()) {
                saveradiobuttondatapage3(button6, masterper, masterperlayout);
            }
        }
        else {
            Toast.makeText(this, "Please select Qualification", Toast.LENGTH_SHORT).show();
        }

    }

    // finding ids radio button page 3 ids
//
//    // finding ids textview
//    TextInputEditText highper = findViewById(R.id.highpercent);
//    TextInputEditText  interper = findViewById(R.id.interpercent);
//    TextInputEditText   underper = findViewById(R.id.undergraduatepercent);
//    TextInputEditText    graduateper = findViewById(R.id.graduatepercent);
//    TextInputEditText   postper = findViewById(R.id.postpercent);
//    TextInputEditText    masterper = findViewById(R.id.masterpercent);

    // layout ids

    void showdTextview(int id) {
        highperlayout.setVisibility(View.GONE);
        interperlayout.setVisibility(View.GONE);
        underperlayout.setVisibility(View.GONE);
        graduateperlayout.setVisibility(View.GONE);
        postperlayout.setVisibility(View.GONE);
        masterperlayout.setVisibility(View.GONE);

        switch (id) {
            case 1:
                highperlayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                interperlayout.setVisibility(View.VISIBLE);
                break;
            case 3:
                underperlayout.setVisibility(View.VISIBLE);
                break;
            case 4:
                graduateperlayout.setVisibility(View.VISIBLE);
                break;
            case 5:
                postperlayout.setVisibility(View.VISIBLE);
                break;
            case 6:
                masterperlayout.setVisibility(View.VISIBLE);
                break;
        }

    }


    void page4selectstudy()
    {
        if (interradio.isChecked() || graduateradio.isChecked()||
                postradio.isChecked()||masterradio.isChecked())
        {
            stepcount++;
            progressbar(stepcount);
        }
        else{

            Toast toast= Toast.makeText(getApplicationContext(),
                    "Please Select Study ", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 100, 50);
            toast.show();
        }

    }


    void page4setdata(int i)
    {
            switch (i) {
                case 1:
                    interradio.setVisibility(View.VISIBLE);
                    graduateradio.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    interradio.setVisibility(View.GONE);
                    break;
                case 3:
                    interradio.setVisibility(View.GONE);
                    graduateradio.setVisibility(View.GONE);
                    break;
                default:
        }
    }


    void Page5nextlayoutCall()
    {
        stepcount++;
        progressbar(stepcount);

    }


    // page 5 setdata
    TextInputEditText reading,writing,listening,speaking,overall,Doverall;
    TextView examtitle;
    TextInputLayout readinglayout, writinglayout,listeninglayout,speakinglayout,overalllayout,Doveralllayout;
    LinearLayout ieltsinputlayout,pteinputlayout,toeflinputlayout,duolinggoinputlayout;
    void page5setdata()
    {

        ieltslinear = findViewById(R.id.ieltslinear);
        ptelinear = findViewById(R.id.ptelinear);
        toefllinear = findViewById(R.id.toefllinear);
        linearduoling = findViewById(R.id.linearduoling);
        donotLinear = findViewById(R.id.donotLinear);

        ieltsinputlayout = findViewById(R.id.ieltsinputlayout);
        pteinputlayout = findViewById(R.id.pteinputlayout);
        toeflinputlayout = findViewById(R.id.toeflinputlayout);
        duolinggoinputlayout = findViewById(R.id.duolinggoinputlayout);





        ieltslinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ieltsinputlayout.setVisibility(View.VISIBLE);
                pteinputlayout.setVisibility(View.GONE);
                toeflinputlayout.setVisibility(View.GONE);
                duolinggoinputlayout.setVisibility(View.GONE);

            }
        });
        ptelinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pteinputlayout.setVisibility(View.VISIBLE);
                ieltsinputlayout.setVisibility(View.GONE);
                toeflinputlayout.setVisibility(View.GONE);
                duolinggoinputlayout.setVisibility(View.GONE);

            }
        });
        toefllinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toeflinputlayout.setVisibility(View.VISIBLE);
                ieltsinputlayout.setVisibility(View.GONE);
                pteinputlayout.setVisibility(View.GONE);
                duolinggoinputlayout.setVisibility(View.GONE);

            }
        });
        donotLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                duolinggoinputlayout.setVisibility(View.VISIBLE);
                ieltsinputlayout.setVisibility(View.GONE);
                pteinputlayout.setVisibility(View.GONE);
                toeflinputlayout.setVisibility(View.GONE);

            }
        });

    }



    void validationPage5(LinearLayout linearlayout)
    {

        SharedPreferences.Editor editor = getSharedPreferences("testScore", Context.MODE_PRIVATE).edit();
        if (linearlayout.getVisibility()==View.VISIBLE)
        {
            String readText = reading.getText().toString();
            String writeText = writing.getText().toString();
            String listenText = listening.getText().toString();
            String speakText = speaking.getText().toString();
            String overText = overall.getText().toString();

            // linear layout 1st
            if (!readText.isEmpty() && !writeText.isEmpty() &&
                    !listenText.isEmpty()&& !speakText.isEmpty() && !overText.isEmpty())
            {

                editor.putString("examname", examtitle.getText().toString());
                editor.putString("read", readText);
                editor.putString("write", writeText);
                editor.putString("listen", listenText);
                editor.putString("speak", speakText);
                editor.putString("overall", overText);
                editor.commit();
                stepcount++;
                progressbar(stepcount);

            }
            else if (readText.isEmpty())
            {
                errorShowFunction(readinglayout, reading);
            }
            else if (writeText.isEmpty())
            {
                errorShowFunction(writinglayout, writing);
            }
            else if (listenText.isEmpty())
            {
                errorShowFunction(listeninglayout, listening);
            }
            else if (speakText.isEmpty())
            {
                errorShowFunction(speakinglayout, speaking);
            }
            else if (overText.isEmpty())
            {
                errorShowFunction(overalllayout, overall);
            }
        }
    }



    void testinputTextWatcher()
    {
        setpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                notmatchedtext.setVisibility(View.GONE);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        reading.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                readinglayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        writing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                writinglayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        listening.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listeninglayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        speaking.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                speakinglayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        overall.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                overalllayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Doverall.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Doveralllayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    void savesharepreferencedata_page5()

    {

    }

    void errorShowFunction(TextInputLayout layout,TextInputEditText text)
    {
        layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
        layout.setError("Required*");
        text.requestFocus();
    }



    void page6setdata()
    {


    }
}