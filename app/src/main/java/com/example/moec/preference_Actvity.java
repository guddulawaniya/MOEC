package com.example.moec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    Button nextbutton;
    LinearLayout page1, page2, page3, page4, page5, page6;
    TextInputLayout highperlayout, interperlayout, underperlayout, graduateperlayout, postperlayout, masterperlayout;
    TextInputEditText highper, interper, underper, graduateper, postper, masterper;
    RadioButton button1, button2, button3, button4, button5, button6, interradio, graduateradio, postradio, masterradio;
    RecyclerView mostpreferedRecyclerview;
    ArrayList<most_prefered_destination_module> mostpreferedlist;
    PinView setpassword,comfirmpassword;

    CheckBox passcheck ;
    TextView notmatchedtext;

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
        page4setdata();
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

        button1 = findViewById(R.id.radioButtonhigh);
        button2 = findViewById(R.id.radioButtoninter);
        button3 = findViewById(R.id.radioButtonunder);
        button4 = findViewById(R.id.radioButtongraduate);
        button5 = findViewById(R.id.radioButtonpost);
        button6 = findViewById(R.id.radioButtonmaster);


        page6TextWatcher();


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


        // mainActivity Code

        index = findViewById(R.id.indexingtext);
        stepView = findViewById(R.id.step_view);
        progressbar(stepcount);

        ImageView backbutton = findViewById(R.id.backbutton);

        SharedPreferences preferences = getSharedPreferences("selectcountry", Context.MODE_PRIVATE);

        int position = preferences.getInt("position", 0);



        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (page1.getVisibility() == View.VISIBLE) {
                    stepcount++;
                    progressbar(stepcount);
                } else if (page2.getVisibility() == View.VISIBLE) {
                    stepcount++;
                    progressbar(stepcount);

                } else if (page3.getVisibility() == View.VISIBLE) {
                    progressbar(stepcount);
                    page3dataset();

                } else if (page4.getVisibility() == View.VISIBLE) {
                    page4selectstudy();

                } else if (page5.getVisibility() == View.VISIBLE) {
                    stepcount++;
                    progressbar(stepcount);

                } else if (page6.getVisibility() == View.VISIBLE) {

                    if (!setpassword.getText().toString().isEmpty() && !comfirmpassword.getText().toString().isEmpty())
                    {
                        if (setpassword.getText().toString().equals(comfirmpassword.getText().toString()))
                        {
                            stepcount++;
                            progressbar(stepcount);
                        }
                        else
                            notmatchedtext.setVisibility(View.VISIBLE);
                        notmatchedtext.setText("Please set password");



                    }
                    else
                    {
                        notmatchedtext.setVisibility(View.VISIBLE);
                        notmatchedtext.setText("Please set password");
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


    void saveradiobuttondatapage3(String text, String percentage, int id) {


        SharedPreferences.Editor editor = getSharedPreferences("qualification", Context.MODE_PRIVATE).edit();
        editor.putString("percentage", percentage);
        editor.putString("educationtext", text);
        editor.putInt("id", id);

        editor.commit();
        stepcount++;
        progressbar(stepcount);


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

    void page6TextWatcher()
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
    }





    // layout setdata


    void page1dataset() {
        mostpreferedlist = new ArrayList<>();
        mostpreferedRecyclerview = findViewById(R.id.mostpreferedRecyclerview);
        mostpreferedRecyclerview.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        // nested scolling set false
        mostpreferedRecyclerview.setNestedScrollingEnabled(false);

        // object Adapters
        most_prefered_destination_Adapter mostAdapter = new most_prefered_destination_Adapter(mostpreferedlist, getApplicationContext());

        // set adapter on recycler view
        mostpreferedRecyclerview.setAdapter(mostAdapter);

        // add data on list
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.uk_flag, "United Kingdom"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.us_flag, "United State"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.flag_canada, "Canada"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.australia_flag, "Australia"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.flag_canada, "Italy"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.germany_flag, "Germany"));
        mostpreferedlist.add(new most_prefered_destination_module(R.drawable.zealand_flag, "new Zealand"));
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


        // object of interest Adapter
        interest_area_Adapter adapter = new interest_area_Adapter(list);


        // set layout manager on recyclerview
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        recyclerView.setAdapter(adapter);

        // add data on list

        list.add(new interest_module(R.drawable.architecture, "Architecture"));
        list.add(new interest_module(R.drawable.computer, "Computer Science"));
        list.add(new interest_module(R.drawable.graphic_design, "Design"));
        list.add(new interest_module(R.drawable.engineering, "Engineering"));
        list.add(new interest_module(R.drawable.business, "Business"));
        list.add(new interest_module(R.drawable.hospitality, "Hospitality & Tourism"));
        list.add(new interest_module(R.drawable.humanities, "Humanities & Social Science"));
        list.add(new interest_module(R.drawable.law, "Law"));
        list.add(new interest_module(R.drawable.management, "Management"));
        list.add(new interest_module(R.drawable.marketing, "Marketing & Advertising"));
        list.add(new interest_module(R.drawable.news, "Media & Journalism"));
        list.add(new interest_module(R.drawable.medical_symbol, "Medical"));
        list.add(new interest_module(R.drawable.creative_thinking, "Performing and Creative Arts"));
        list.add(new interest_module(R.drawable.science, "Science"));
        list.add(new interest_module(R.drawable.sports, "Sport & Nutrition"));
        list.add(new interest_module(R.drawable.translation, "Languages"));
        list.add(new interest_module(R.drawable.education, "Education"));
    }


    void page3dataset() {
        if (button1.isChecked() || button2.isChecked() ||
                button3.isChecked() || button4.isChecked() || button5.isChecked() || button6.isChecked())
        {
            if (button1.isChecked()) {

                if (!highper.getText().toString().isEmpty()) {
                    saveradiobuttondatapage3(button1.getText().toString(), highper.getText().toString(), 1);

                } else {
                    highperlayout.setErrorEnabled(true);
                    highperlayout.setHelperText("Percentage Required");
                    highperlayout.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.secondarycolor)));
                }
            }
            if (button2.isChecked()) {
                if (!interper.getText().toString().isEmpty()) {
                    saveradiobuttondatapage3(button2.getText().toString(), interper.getText().toString(), 2);

                } else {
                    interperlayout.setErrorEnabled(true);
                    interperlayout.setHelperText("Percentage Required");
                    interperlayout.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.secondarycolor)));
                }
            }
            if (button3.isChecked()) {
                if (!underper.getText().toString().isEmpty()) {
                    saveradiobuttondatapage3(button3.getText().toString(), underper.getText().toString(), 3);


                } else {
                    underperlayout.setErrorEnabled(true);
                    underperlayout.setHelperText("Percentage Required");
                    underperlayout.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.secondarycolor)));
                }
            }
            if (button4.isChecked()) {
                if (!graduateper.getText().toString().isEmpty()) {
                    saveradiobuttondatapage3(button4.getText().toString(), graduateper.getText().toString(), 4);

                } else {
                    graduateperlayout.setErrorEnabled(true);
                    graduateperlayout.setHelperText("Percentage Required");
                    graduateperlayout.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.secondarycolor)));
                }
            }
            if (button5.isChecked()) {
                if (!postper.getText().toString().isEmpty()) {
                    saveradiobuttondatapage3(button5.getText().toString(), postper.getText().toString(), 5);

                } else {
                    postperlayout.setErrorEnabled(true);
                    postperlayout.setHelperText("Percentage Required");

                    postperlayout.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.secondarycolor)));
                }
            }
            if (button6.isChecked()) {
                if (!masterper.getText().toString().isEmpty()) {
                    saveradiobuttondatapage3(button6.getText().toString(), masterper.getText().toString(), 6);

                } else {
                    masterperlayout.setErrorEnabled(true);
                    masterperlayout.setHelperText("Percentage Required");
                    masterperlayout.setBoxStrokeErrorColor(ColorStateList.valueOf(getResources().getColor(R.color.secondarycolor)));
                }
            }
        } else {
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


    void page4setdata()
    {

        SharedPreferences preferences1 = getSharedPreferences("qualification",Context.MODE_PRIVATE);
        int id = preferences1.getInt("id",0);

        for (int i =2;i<id+1;++i)
        {
            switch (i) {
                case 2:
                    interradio.setVisibility(View.GONE);
                    break;
                case 3:

                    graduateradio.setVisibility(View.GONE);
                    break;
                default:
        }

        }
    }



    // page 5 setdata

    TextView reading,writing,listening,speaking,overall;
    TextInputLayout readinglayout, writinglayout,listeninglayout,speakinglayout,overalllayout;

    void page5setdata()
    {
        String[] list = {"I don't have this","I will appear soon","IELTS","PTE","TOEFL","Duoling English Test","GRE","GMAT"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item ,list);


        AutoCompleteTextView selectexam = findViewById(R.id.selectexam_dropdown);


        LinearLayout linear2 = findViewById(R.id.secoundlinear);
        LinearLayout linea1 = findViewById(R.id.linear1);
        LinearLayout linea3 = findViewById(R.id.linear3);


        reading = findViewById(R.id.readingtext);
        writing = findViewById(R.id.writingtext);
        listening = findViewById(R.id.listeningtext);
        speaking = findViewById(R.id.speakingtext);
        overall = findViewById(R.id.overalltext);
        readinglayout = findViewById(R.id.readinginputfieldslayout);
        writinglayout = findViewById(R.id.writinginputlayout);
        listeninglayout = findViewById(R.id.listeninglayout);
        speakinglayout = findViewById(R.id.speakinginputlayout);
        overalllayout = findViewById(R.id.overallinputlayout);

        TextView examtitle = findViewById(R.id.examtitle);
        selectexam.setDropDownBackgroundResource(R.color.background_blue_shadew);

        selectexam.setAdapter(adapter);

        selectexam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                examtitle.setText("Enter "+list[i]+ "Score");
                if (i<2)
                {
                    examtitle.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linea3.setVisibility(View.GONE);
                    linea1.setVisibility(View.GONE);
                }
                else if (i>1 && i<5) {

                    examtitle.setVisibility(View.VISIBLE);
                    linear2.setVisibility(View.GONE);
                    linea3.setVisibility(View.GONE);
                    linea1.setVisibility(View.VISIBLE);

                }

                else if (i>5)
                {


                    examtitle.setVisibility(View.VISIBLE);
                    linea1.setVisibility(View.GONE);
                    linea3.setVisibility(View.GONE);
                    linear2.setVisibility(View.VISIBLE);

                }
                else if (i==5)
                {
                    examtitle.setVisibility(View.VISIBLE);

                    linea1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linea3.setVisibility(View.VISIBLE);

                }

            }
        });
    }



    void page6setdata()
    {


    }
}