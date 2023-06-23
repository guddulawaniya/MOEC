package com.example.moec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

public class preference_Actvity extends AppCompatActivity  {

    TextView index;
    StepView stepViewProgressBar;
    int stepcount = 1;

    LinearLayout page1, page2, page3, page4, page5, page6;
    Button nextbutton;

    page6 page6class;
    page5 page5class;
    page3 page3class;
    page4 page4class;
    page2 page2class;
    page1 page1class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_actvity);



        // Fragment Layout ids show and hide

        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page3 = findViewById(R.id.page3);
        page4 = findViewById(R.id.page4);
        page5 = findViewById(R.id.page5);
        page6 = findViewById(R.id.page6);


        // classes Objects

        page1class = new page1();
        page2class = new page2();
        page3class = new page3();
        page4class = new page4();
        page5class = new page5();
        page6class = new page6();

        // page 4 i want to study in aboard radio button



        page1class.setdataonRecyclerview();
        page2class.setdataonRecyclerview();
        page3class.onclickbuttobn();
        page5class.cardsClickAndShow_layout();




        textwatcher();


        // mainActivity Code

        index = findViewById(R.id.indexingtext);
        nextbutton = findViewById(R.id.nextbutton);
        stepViewProgressBar = findViewById(R.id.step_view);
        progressbar(stepcount);

        ImageView backbutton = findViewById(R.id.backbutton);


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextbottonvalidation();

            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    void nextbottonvalidation()
    {
        if (page1.getVisibility() == View.VISIBLE) {

            if (page2class.checkselectcountry)
            {
                page2class.checkselectcountry = false;
                stepcount++;
                progressbar(stepcount);
            }
            else {
                Toast.makeText(preference_Actvity.this, "Please Select Country", Toast.LENGTH_SHORT).show();
            }

        } else if (page2.getVisibility() == View.VISIBLE) {

            if (page2class.checkselectcountry)
            {
                page2class.checkselectcountry = false;
                stepcount++;
                progressbar(stepcount);
            }
            else {

                Toast.makeText(preference_Actvity.this, "Please Select Interest", Toast.LENGTH_SHORT).show();
            }


        } else if (page3.getVisibility() == View.VISIBLE) {
            progressbar(stepcount);
            page3class.page3dataset();

        } else if (page4.getVisibility() == View.VISIBLE) {
            page4class.page4selectstudy();

        } else if (page5.getVisibility() == View.VISIBLE) {


            page5class.validation();



        } else if (page6.getVisibility() == View.VISIBLE) {


            page6class.validation();
        }


    }





    void textwatcher()
    {

        page6class.setpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                page6class.notmatchedtext.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        page6class.comfirmpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                page6class.notmatchedtext.setVisibility(View.GONE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        page5class.reading.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                page5class. readinglayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        page5class.writing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                page5class.writinglayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        page5class.listening.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                page5class.listeninglayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        page5class.speaking.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                page5class.speakinglayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        page5class.overall.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                page5class.overalllayout.setErrorEnabled(false);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//            page3class.percentage.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                layout.setErrorEnabled(false);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

    }



    void progressbar(int stepcount) {
        index.setText(stepcount + " of 7 ");
        stepViewProgressBar.getState()
                .animationType(StepView.ANIMATION_LINE)
                .nextStepLineColor(ContextCompat.getColor(getApplicationContext(), R.color.background_blue_shadew))
                .doneStepMarkColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .stepsNumber(7)
                .nextStepCircleEnabled(false)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(6)
                .commit();
        stepViewProgressBar.go(stepcount, true);
        changelayout(stepcount);


    }


    void changelayout(int id) {
        switch (id) {
            case 1:
                page1.setVisibility(View.VISIBLE);
                page2.setVisibility(View.GONE);

                break;
            case 2:
                page1.setVisibility(View.GONE);
                page2.setVisibility(View.VISIBLE);
                page3.setVisibility(View.GONE);
                break;
            case 3:

                page2.setVisibility(View.GONE);
                page3.setVisibility(View.VISIBLE);
                page4.setVisibility(View.GONE);
                break;
            case 4:

                page3.setVisibility(View.GONE);
                page4.setVisibility(View.VISIBLE);
                page5.setVisibility(View.GONE);

                break;
            case 5:
                page4.setVisibility(View.GONE);
                page5.setVisibility(View.VISIBLE);
                page6.setVisibility(View.GONE);
                break;
            case 6:
                page5.setVisibility(View.GONE);
                page6.setVisibility(View.VISIBLE);

                break;
            case 7:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;
        }

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






    // page number 1 class
    class page1{

        ArrayList<most_prefered_destination_module> mostpreferedlist=  new ArrayList<>();


       RecyclerView mostpreferedRecyclerview = findViewById(R.id.mostpreferedRecyclerview);
        void setdataonRecyclerview() {


            mostpreferedRecyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

            onClickInterface  onclickInterface = new onClickInterface() {
                @Override
                public void setClick(int position) {
                    if (position>-1)
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("qualification", Context.MODE_PRIVATE).edit();
                        editor.putString("countryname", String.valueOf(mostpreferedlist.get(position)));
                        editor.commit();
                        page2class.checkselectcountry=true;

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
    }




    // page number 2 class

    class page2
    {
        RecyclerView recyclerView = findViewById(R.id.interest_recyclerview);
        boolean checkselectcountry= false;
        ArrayList<interest_module> list = new ArrayList<>();
        void setdataonRecyclerview() {

          onClickInterface  onclickInterface = new onClickInterface() {
                @Override
                public void setClick(int position) {
                    if (!checkselectcountry)
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("qualification", Context.MODE_PRIVATE).edit();
                        editor.putString("interestarea", String.valueOf(list.get(position)));
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

    }



    // page number 3 class

    class page3 {


        RadioButton button1 = findViewById(R.id.radioButtonhigh);
        RadioButton button2 = findViewById(R.id.radioButtoninter);
        RadioButton button3 = findViewById(R.id.radioButtonunder);
        RadioButton button4 = findViewById(R.id.radioButtongraduate);
        RadioButton button5 = findViewById(R.id.radioButtonpost);
        RadioButton  button6 = findViewById(R.id.radioButtonmaster);


        // finding ids textview
        TextInputEditText  highper = findViewById(R.id.highpercent);
        TextInputEditText  interper = findViewById(R.id.interpercent);
        TextInputEditText  underper = findViewById(R.id.undergraduatepercent);
        TextInputEditText graduateper = findViewById(R.id.graduatepercent);
        TextInputEditText postper = findViewById(R.id.postpercent);
        TextInputEditText  masterper = findViewById(R.id.masterpercent);



        // layout textfields
        TextInputLayout highperlayout = findViewById(R.id.highpercentlayout);
        TextInputLayout  interperlayout = findViewById(R.id.interpercentlayout);
        TextInputLayout  underperlayout = findViewById(R.id.undergraduatepercentlayout);
        TextInputLayout  graduateperlayout = findViewById(R.id.graduatepercentlayout);
        TextInputLayout postperlayout = findViewById(R.id.postpercentlayout);
        TextInputLayout  masterperlayout = findViewById(R.id.masterpercentlayout);


        void show_and_hide_layout(int id) {
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

        void onclickbuttobn()
        {
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_and_hide_layout(1);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_and_hide_layout(2);
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_and_hide_layout(3);

                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_and_hide_layout(4);

                }
            });
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_and_hide_layout(5);

                }
            });
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show_and_hide_layout(6);

                }
            });

        }



        void page3dataset() {
            if (button1.isChecked() || button2.isChecked() ||
                    button3.isChecked() || button4.isChecked() || button5.isChecked() || button6.isChecked())
            {
                if (button1.isChecked()) {


                   saveradiobuttondatapage3(button1, highper, highperlayout);
                   page4class.page4setdata(1);
                }
                if (button2.isChecked()) {
                    saveradiobuttondatapage3(button2, interper, interperlayout);
                    page4class.page4setdata(2);
                }
                if (button3.isChecked()) {

                    page4class.page4setdata(3);
                    saveradiobuttondatapage3(button3, underper, underperlayout);
                }
                if (button4.isChecked()) {
                    page4class.page4setdata(3);
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
                Toast.makeText(getApplicationContext(), "Please select Qualification", Toast.LENGTH_SHORT).show();
            }

        }


        void saveradiobuttondatapage3(RadioButton text, TextInputEditText percentage,TextInputLayout layout)
        {
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
                page5class.errorShowFunction(layout,percentage);

            }
        }


    }



    // page number 4 class

    class page4{

        RadioButton interradio = findViewById(R.id.radioButtoninterpage4);
        RadioButton graduateradio = findViewById(R.id.radioButtongraduatepage4);
        RadioButton postradio = findViewById(R.id.radioButtonpostpage4);
        RadioButton masterradio = findViewById(R.id.radioButtonmasterpage4);
        void page4selectstudy() {
            if (interradio.isChecked() || graduateradio.isChecked() ||
                    postradio.isChecked() || masterradio.isChecked()) {
                stepcount++;
                progressbar(stepcount);
            } else {

                Toast toast = Toast.makeText(getApplicationContext(),
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

    }




    // Page number 5 class

    class page5 {
        boolean checkselect = true;
        public LinearLayout ieltslinear = findViewById(R.id.ieltslinear);
        LinearLayout ptelinear = findViewById(R.id.ptelinear);
        LinearLayout toefllinear = findViewById(R.id.toefllinear);
        LinearLayout linearduoling = findViewById(R.id.linearduoling);
        LinearLayout donotLinear = findViewById(R.id.donotLinear);
        LinearLayout ieltsinputlayout = findViewById(R.id.ieltsinputlayout);
        LinearLayout pteinputlayout = findViewById(R.id.pteinputlayout);
        LinearLayout toeflinputlayout = findViewById(R.id.toeflinputlayout);
        LinearLayout duolinggoinputlayout = findViewById(R.id.duolinggoinputlayout);



        // input fields
        TextInputEditText reading = findViewById(R.id.readinginput);
        TextInputEditText writing = findViewById(R.id.writinginput);
        TextInputEditText listening = findViewById(R.id.listeninginput);
        TextInputEditText speaking = findViewById(R.id.speakinginput);
        TextInputEditText overall = findViewById(R.id.overallinput);


        // textlayout

        TextInputLayout readinglayout = findViewById(R.id.RLayout);
        TextInputLayout writinglayout = findViewById(R.id.wlayout);
        TextInputLayout listeninglayout = findViewById(R.id.listeninglayout);
        TextInputLayout speakinglayout = findViewById(R.id.slayout);
        TextInputLayout overalllayout = findViewById(R.id.Overlayout);

        void cardsClickAndShow_layout()
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
                    pteinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);
                    ptelinear.setBackgroundColor(Color.WHITE);
                    toefllinear.setBackgroundColor(Color.WHITE);
                    donotLinear.setBackgroundColor(Color.WHITE);
                    linearduoling.setBackgroundColor(Color.WHITE);

                    if (ieltsinputlayout.getVisibility()==View.VISIBLE)
                    {
                        ieltslinear.setBackgroundColor(Color.WHITE);
                        ieltsinputlayout.setVisibility(View.GONE);

                    }
                    else if (ieltsinputlayout.getVisibility()==View.GONE)
                    {
                        ieltsinputlayout.setVisibility(View.VISIBLE);
                        ieltslinear.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));
                    }

                }
            });


            ptelinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ieltsinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);


                    linearduoling.setBackgroundColor(Color.WHITE);
                    toefllinear.setBackgroundColor(Color.WHITE);
                    ieltslinear.setBackgroundColor(Color.WHITE);
                    donotLinear.setBackgroundColor(Color.WHITE);


                    if (pteinputlayout.getVisibility()==View.VISIBLE)
                    {

                        ptelinear.setBackgroundColor(Color.WHITE);
                        pteinputlayout.setVisibility(View.GONE);
                    }else if (pteinputlayout.getVisibility()==View.GONE)
                    {
                        ptelinear.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));
                        pteinputlayout.setVisibility(View.VISIBLE);
                    }


                }
            });
            toefllinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ieltsinputlayout.setVisibility(View.GONE);
                    pteinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);

                    linearduoling.setBackgroundColor(Color.WHITE);
                    ptelinear.setBackgroundColor(Color.WHITE);
                    ieltslinear.setBackgroundColor(Color.WHITE);
                    donotLinear.setBackgroundColor(Color.WHITE);


                    if (toeflinputlayout.getVisibility()==View.VISIBLE)
                    {
                        toefllinear.setBackgroundColor(Color.WHITE);
                        toeflinputlayout.setVisibility(View.GONE);
                    }
                    else if (toeflinputlayout.getVisibility()==View.GONE)
                    {
                        toeflinputlayout.setVisibility(View.VISIBLE);
                        toefllinear.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));

                    }

                }
            });
            donotLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ieltsinputlayout.setVisibility(View.GONE);
                    pteinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);

                    ptelinear.setBackgroundColor(Color.WHITE);
                    toefllinear.setBackgroundColor(Color.WHITE);
                    ieltslinear.setBackgroundColor(Color.WHITE);
                    linearduoling.setBackgroundColor(Color.WHITE);

                    if (checkselect)
                    {

                        donotLinear.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));
                        checkselect= false;

                    }
                    else
                    {
                        checkselect= true;
                        donotLinear.setBackgroundColor(Color.WHITE);
                    }
                }
            });
            linearduoling.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ieltsinputlayout.setVisibility(View.GONE);
                    pteinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);


                    ptelinear.setBackgroundColor(Color.WHITE);
                    toefllinear.setBackgroundColor(Color.WHITE);
                    ieltslinear.setBackgroundColor(Color.WHITE);
                    donotLinear.setBackgroundColor(Color.WHITE);

                    if (duolinggoinputlayout.getVisibility()==View.VISIBLE)
                    {
                        linearduoling.setBackgroundColor(Color.WHITE);
                        duolinggoinputlayout.setVisibility(View.GONE);
                    }  else if (duolinggoinputlayout.getVisibility()==View.GONE)
                    {
                        linearduoling.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));
                        duolinggoinputlayout.setVisibility(View.VISIBLE);
                    }

                }
            });

        }


        void validation()
        {
            if(page5class.ieltsinputlayout.getVisibility()==View.VISIBLE ||page5class.pteinputlayout.getVisibility()==View.VISIBLE ||
                    page5class.duolinggoinputlayout.getVisibility()==View.VISIBLE||
                    page5class.toeflinputlayout.getVisibility()==View.VISIBLE ||!page5class.checkselect){
                if (page5class.ieltsinputlayout.getVisibility() == View.VISIBLE) {
                    validationInputFields();

                } else if (pteinputlayout.getVisibility() == View.VISIBLE) {
                    validationInputFields();

                } else if (toeflinputlayout.getVisibility() == View.VISIBLE) {
                    validationInputFields();

                } else if (duolinggoinputlayout.getVisibility() == View.VISIBLE) {
                    validationInputFields();

                } else if (!checkselect) {

                    progressbar(++stepcount);

                }
            }
            else {

                Toast.makeText(preference_Actvity.this, "Please Select Course", Toast.LENGTH_SHORT).show();
            }
        }
        void validationInputFields()
        {

            SharedPreferences.Editor editor = getSharedPreferences("testScore", Context.MODE_PRIVATE).edit();
                String readText = reading.getText().toString();
                String writeText = writing.getText().toString();
                String listenText = listening.getText().toString();
                String speakText = speaking.getText().toString();
                String overText = overall.getText().toString();

                // linear layout 1st
                if (!readText.isEmpty() && !writeText.isEmpty() &&
                        !listenText.isEmpty()&& !speakText.isEmpty() && !overText.isEmpty())
                {

//                    editor.putString("examname", examtitle.getText().toString());
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


        void errorShowFunction(TextInputLayout layout,TextInputEditText text)
        {
            layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
            layout.setError("Required*");
            text.requestFocus();
        }


        void show_and_hide_layout()
        {

            ieltslinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pteinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);
                    ptelinear.setBackgroundColor(Color.WHITE);
                    toefllinear.setBackgroundColor(Color.WHITE);
                    donotLinear.setBackgroundColor(Color.WHITE);
                    linearduoling.setBackgroundColor(Color.WHITE);

                    if (ieltsinputlayout.getVisibility()==View.VISIBLE)
                    {
                        ieltslinear.setBackgroundColor(Color.WHITE);
                        ieltsinputlayout.setVisibility(View.GONE);

                    }
                    else if (ieltsinputlayout.getVisibility()==View.GONE)
                    {
                        ieltsinputlayout.setVisibility(View.VISIBLE);
                        ieltslinear.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));
                    }

                }
            });
            ptelinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ieltsinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);


                    linearduoling.setBackgroundColor(Color.WHITE);
                    toefllinear.setBackgroundColor(Color.WHITE);
                    ieltslinear.setBackgroundColor(Color.WHITE);
                    donotLinear.setBackgroundColor(Color.WHITE);


                    if (pteinputlayout.getVisibility()==View.VISIBLE)
                    {

                        ptelinear.setBackgroundColor(Color.WHITE);
                        pteinputlayout.setVisibility(View.GONE);
                    }else if (pteinputlayout.getVisibility()==View.GONE)
                    {
                        ptelinear.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));
                        pteinputlayout.setVisibility(View.VISIBLE);
                    }


                }
            });
            toefllinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ieltsinputlayout.setVisibility(View.GONE);
                    pteinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);

                    linearduoling.setBackgroundColor(Color.WHITE);
                    ptelinear.setBackgroundColor(Color.WHITE);
                    ieltslinear.setBackgroundColor(Color.WHITE);
                    donotLinear.setBackgroundColor(Color.WHITE);


                    if (toeflinputlayout.getVisibility()==View.VISIBLE)
                    {
                        toefllinear.setBackgroundColor(Color.WHITE);
                        toeflinputlayout.setVisibility(View.GONE);
                    }
                    else if (toeflinputlayout.getVisibility()==View.GONE)
                    {
                        toeflinputlayout.setVisibility(View.VISIBLE);
                        toefllinear.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));

                    }

                }
            });

            linearduoling.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ieltsinputlayout.setVisibility(View.GONE);
                    pteinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);


                    ptelinear.setBackgroundColor(Color.WHITE);
                    toefllinear.setBackgroundColor(Color.WHITE);
                    ieltslinear.setBackgroundColor(Color.WHITE);
                    donotLinear.setBackgroundColor(Color.WHITE);

                    if (duolinggoinputlayout.getVisibility()==View.VISIBLE)
                    {
                        linearduoling.setBackgroundColor(Color.WHITE);
                        duolinggoinputlayout.setVisibility(View.GONE);
                    }  else if (duolinggoinputlayout.getVisibility()==View.GONE)
                    {
                        linearduoling.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));
                        duolinggoinputlayout.setVisibility(View.VISIBLE);
                    }

                }
            });
            donotLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ieltsinputlayout.setVisibility(View.GONE);
                    pteinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);

                    ptelinear.setBackgroundColor(Color.WHITE);
                    toefllinear.setBackgroundColor(Color.WHITE);
                    ieltslinear.setBackgroundColor(Color.WHITE);
                    linearduoling.setBackgroundColor(Color.WHITE);

                    if (checkselect)
                    {

                        donotLinear.setBackgroundColor(getResources().getColor(R.color.background_blue_shadew));
                        checkselect= false;

                    }
                    else
                    {
                        checkselect= true;
                        donotLinear.setBackgroundColor(Color.WHITE);
                    }
                }
            });

        }

    }



    // page number 6 class

    class page6{


        PinView setpassword=findViewById(R.id.setpassword);
        PinView comfirmpassword=findViewById(R.id.comfirmpassword);
        CheckBox passcheck = findViewById(R.id.passcheck);
        TextView notmatchedtext = findViewById(R.id.notmatchedtext);


        void validation()
        {
            if (!setpassword.getText().toString().isEmpty() && !comfirmpassword.getText().toString().isEmpty())
            {
                if (setpassword.getText().toString().equals(comfirmpassword.getText().toString()))
                {
                    if (passcheck.isChecked())
                    {

                        SharedPreferences.Editor editor = getSharedPreferences("preference",MODE_PRIVATE).edit();
                        editor.putString("password",setpassword.getText().toString());
                        editor.putBoolean("checkpass",true);
                        editor.commit();
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


}