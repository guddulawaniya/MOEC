package com.example.moec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.interest_area_Adapter;
import com.example.moec.Adapters.most_prefered_destination_Adapter;
import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.get_country_data;
import com.example.moec.JavaClass.get_subject_data;
import com.example.moec.JavaClass.update_preference;
import com.example.moec.ModulesClass.module_all_program;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Preference_update_Activity extends AppCompatActivity {

    page1 page1object;
    page2 page2object;
    page3 page3object;

    page5 page5object;
    ProgressBar progressBar;
    LinearLayout linearpage1, linearpage2,linearpage3,linearpage4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_update);

        Button savebutton = findViewById(R.id.savebutton);
        progressBar = findViewById(R.id.progressBar);

        linearpage1 = findViewById(R.id.linearpage1);
        linearpage2 = findViewById(R.id.linearpage2);
        linearpage3 = findViewById(R.id.linearpage3);
        linearpage4 = findViewById(R.id.linearpage4);

        linearpage1.setVisibility(View.GONE);
        linearpage2.setVisibility(View.GONE);
        linearpage3.setVisibility(View.GONE);
        linearpage4.setVisibility(View.GONE);

        page1object = new page1();
        page2object = new page2();
        page3object = new page3();
        page5object = new page5();

        page1object.setdataonRecyclerview();
        page2object.setdataonRecyclerview();
        page3object.onclickbuttobn();
        page5object.cardsClickAndShow_layout();

        textwatcher(page5object.readinglayout,page5object.reading);
        textwatcher(page5object.writinglayout,page5object.writing);
        textwatcher(page5object.listeninglayout,page5object.listening);
        textwatcher(page5object.speakinglayout,page5object.speaking);
        textwatcher(page5object.overalllayout,page5object.overall);

        textwatcher(page5object.ptereadinglayout,page5object.ptereading);
        textwatcher(page5object.ptewritinglayout,page5object.ptewriting);
        textwatcher(page5object.ptelisteninglayout,page5object.ptelistening);
        textwatcher(page5object.ptespeakinglayout,page5object.ptespeaking);
        textwatcher(page5object.pteoveralllayout,page5object.pteoverall);

        textwatcher(page5object.toefreadinglayout,page5object.toefreading);
        textwatcher(page5object.toefwritinglayout,page5object.toefwriting);
        textwatcher(page5object.toeflisteninglayout,page5object.toeflistening);
        textwatcher(page5object.toefspeakinglayout,page5object.toefspeaking);
        textwatcher(page5object.toefoveralllayout,page5object.toefoverall);

        textwatcher(page5object.duoreadinglayout,page5object.duoreading);
        textwatcher(page5object.duowritinglayout,page5object.duowriting);
        textwatcher(page5object.duolisteninglayout,page5object.duolistening);
        textwatcher(page5object.duospeakinglayout,page5object.duospeaking);
        textwatcher(page5object.duooveralllayout,page5object.duooverall);

        textwatcher(page5object.otherExam_layout,page5object.othersexaminput);

        Intent intent = getIntent();
        int id = intent.getIntExtra("layoutid",0);
        String title = intent.getStringExtra("title");

        // finding the ids Textview toolbar
        TextView toolbartitle = findViewById(R.id.toolbar_title);


        TextView clearbutton = findViewById(R.id.cleartext);

        // set the text on toolbar textview

        clearbutton.setVisibility(View.GONE);

        // backbutton
        ImageView backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linearpage1.getVisibility() == View.VISIBLE)
                {

                    savedata();
                 onBackPressed();

                } else if (linearpage2.getVisibility() == View.VISIBLE) {
                    savedata();

                    onBackPressed();
                } else if (linearpage3.getVisibility() == View.VISIBLE) {
                 page3object.page3dataset();


                }
                else if (linearpage4.getVisibility() == View.VISIBLE) {


                    page5object.validation();

                }


            }
        });

        switch (id)
        {
            case 1:
                linearpage1.setVisibility(View.VISIBLE);
                toolbartitle.setText(title);
            break;
            case 2:
                toolbartitle.setText(title);
                linearpage2.setVisibility(View.VISIBLE);
            break;
            case 3:
                toolbartitle.setText(title);
                linearpage3.setVisibility(View.VISIBLE);
            break;
            case 4:
                linearpage4.setVisibility(View.VISIBLE);
                toolbartitle.setText(title);
            break;
        }
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }


    private void textwatcher(TextInputLayout layout,TextInputEditText text)
    {
        text.addTextChangedListener(new TextWatcher() {
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

   private class page1{

        RecyclerView mostpreferedRecyclerview = findViewById(R.id.mostpreferedRecyclerview);
        void setdataonRecyclerview() {


            onClickInterface  onclickInterface = new onClickInterface() {
                @Override
                public void setClick(int position, String text) {
                    if (position>-1)
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();
                        editor.putString("pre_country",text);
                        editor.commit();
                    }

                }
            };

         //   new get_country_data(progressBar,getApplicationContext(),mostpreferedRecyclerview,config.Base_url+"crmcountriesApiData",onclickInterface);


        }
    }
   private class page2 {
        RecyclerView recyclerView = findViewById(R.id.interest_recyclerview);

        ArrayList<module_all_program> list = new ArrayList<>();
        void setdataonRecyclerview() {

            onClickInterface  onclickInterface = new onClickInterface() {
                @Override
                public void setClick(int position, String text) {
                    if (position>-1)
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();
                        editor.putString("interest", text);
                        editor.commit();


                    }

                }
            };

         //   new get_subject_data(progressBar,list,getApplicationContext(),recyclerView,config.Base_url+"crmsubjectApiData",onclickInterface);


        }

    }
   private class page3 {


        RadioButton button1 = findViewById(R.id.radioButtonhigh);
        RadioButton button2 = findViewById(R.id.radioButtoninter);
        RadioButton button3 = findViewById(R.id.radioButtonunder);
        RadioButton button4 = findViewById(R.id.radioButtongraduate);
        RadioButton button5 = findViewById(R.id.radioButtonpost);
        RadioButton  button6 = findViewById(R.id.radioButtonmaster);


        // finding ids textview
        TextInputEditText highper = findViewById(R.id.highpercent);
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
                }
                if (button2.isChecked()) {
                    saveradiobuttondatapage3(button2, interper, interperlayout);
                }
                if (button3.isChecked()) {

                    saveradiobuttondatapage3(button3, underper, underperlayout);
                }
                if (button4.isChecked()) {
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

                SharedPreferences.Editor editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();
                editor.putString("percentage", percentageText);
                editor.putString("qualification", radiotext);
                editor.commit();
                savedata();
                onBackPressed();
            }
            else {
                page5object.errorShowFunction(layout,percentage);

            }
        }


    }
    void savedata()
    {
        SharedPreferences preferences = getSharedPreferences("registrationform", Context.MODE_PRIVATE);
        String userid = preferences.getString("userid",null);
        String pre_country = preferences.getString("pre_country",null);
        String interest = preferences.getString("interest",null);
        String qualification = preferences.getString("qualification",null);
        String edu_marsks = preferences.getString("percentage",null);
        String examname = preferences.getString("examname",null);
        String writeText = preferences.getString("write",null);
        String readText = preferences.getString("read",null);
        String listenText = preferences.getString("listen",null);
        String speakText = preferences.getString("speak",null);
        String overText = preferences.getString("overall",null);

        String registrationURL = config.Base_url + "setPreferenceApiData?" +
                "user_id=" +userid+
                "&des_country=" + pre_country +
                "&intrest=" + interest +
                "&qualification=" + qualification +
                "&edu_marsks=" + edu_marsks +
                "&englishtest=" + examname +
                "&writingscore=" + writeText +
                "&listeningscore=" + readText +
                "&readingscore=" + listenText +
                "&speakingscore=" + speakText +
                "&over_allscore=" + overText;

    new update_preference(Preference_update_Activity.this,registrationURL);
    }
   private class page5 {

        // images cards

        CheckBox checkBox = findViewById(R.id.passcheckexam);
        CardView ielts_card = findViewById(R.id.ielts_card);
        CardView pte_card = findViewById(R.id.pte_card);
        CardView toefl_card = findViewById(R.id.toefl_card);
        CardView duoling_card = findViewById(R.id.duoling_card);
        CardView othercard = findViewById(R.id.othercard);



        // hide linear layouts
        LinearLayout ieltsinputlayout = findViewById(R.id.ieltsinputlayout);
        LinearLayout pteinputlayout = findViewById(R.id.pte_Form_linear);
        LinearLayout toeflinputlayout = findViewById(R.id.toef_Form_linear);
        LinearLayout duolinggoinputlayout = findViewById(R.id.duoling_Form_linear);
        LinearLayout otherinputlayout = findViewById(R.id.other_from_linear);



        // IELTS layout input ids
        TextInputEditText reading = findViewById(R.id.readinginput);
        TextInputEditText writing = findViewById(R.id.writinginput);
        TextInputEditText listening = findViewById(R.id.listeninginput);
        TextInputEditText speaking = findViewById(R.id.speakinginput);
        TextInputEditText overall = findViewById(R.id.overallinput);

        // PTE layout input ids

        TextInputEditText ptereading = findViewById(R.id.ptereadinginput);
        TextInputEditText ptewriting = findViewById(R.id.ptewritinginput);
        TextInputEditText ptelistening = findViewById(R.id.ptelisteninginput);
        TextInputEditText ptespeaking = findViewById(R.id.ptespeakinginput);
        TextInputEditText pteoverall = findViewById(R.id.pteoverallinput);

        // TOEFL  layout input ids

        TextInputEditText toefreading = findViewById(R.id.toefreadinginput);
        TextInputEditText toefwriting = findViewById(R.id.toefwritinginput);
        TextInputEditText toeflistening = findViewById(R.id.toeflisteninginput);
        TextInputEditText toefspeaking = findViewById(R.id.toefspeakinginput);
        TextInputEditText toefoverall = findViewById(R.id.toefoverallinput);

        // duolingo english test layout ids
        TextInputEditText duoreading = findViewById(R.id.duoreadinginput);
        TextInputEditText duowriting = findViewById(R.id.duowritinginput);
        TextInputEditText duolistening = findViewById(R.id.duolisteninginput);
        TextInputEditText duospeaking = findViewById(R.id.duospeakinginput);
        TextInputEditText duooverall = findViewById(R.id.duooverallinput);
        TextInputEditText othersexaminput = findViewById(R.id.othersexaminput);


        // text  layout

        TextInputLayout readinglayout = findViewById(R.id.RLayout);
        TextInputLayout writinglayout = findViewById(R.id.wlayout);
        TextInputLayout listeninglayout = findViewById(R.id.listeninglayout);
        TextInputLayout speakinglayout = findViewById(R.id.slayout);
        TextInputLayout overalllayout = findViewById(R.id.Overlayout);

        TextInputLayout ptereadinglayout = findViewById(R.id.ptereadinglayout);
        TextInputLayout ptewritinglayout = findViewById(R.id.ptewritinglayout);
        TextInputLayout ptelisteninglayout = findViewById(R.id.ptelisteninglayout);
        TextInputLayout ptespeakinglayout = findViewById(R.id.ptespeakinglayout);
        TextInputLayout pteoveralllayout = findViewById(R.id.pteoveralllayout);

        TextInputLayout toefreadinglayout = findViewById(R.id.toeflreadinglayout);
        TextInputLayout toefwritinglayout = findViewById(R.id.toefwritinglayout);
        TextInputLayout toeflisteninglayout = findViewById(R.id.toeflisteninglayout);
        TextInputLayout toefspeakinglayout = findViewById(R.id.toefspeakinglayout);
        TextInputLayout toefoveralllayout = findViewById(R.id.toefoveralllayout);

        TextInputLayout duoreadinglayout = findViewById(R.id.duolreadinglayout);
        TextInputLayout duowritinglayout = findViewById(R.id.duowritinglayout);
        TextInputLayout duolisteninglayout = findViewById(R.id.duolisteninglayout);
        TextInputLayout duospeakinglayout = findViewById(R.id.duospeakinglayout);
        TextInputLayout duooveralllayout = findViewById(R.id.duooveralllayout);
        TextInputLayout otherExam_layout = findViewById(R.id.otherExam_layout);



        void cardsClickAndShow_layout()
        {
            ielts_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pteinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);
                    otherinputlayout.setVisibility(View.GONE);


                    if (ieltsinputlayout.getVisibility()==View.VISIBLE)
                    {

                        TransitionManager.beginDelayedTransition(ielts_card, new AutoTransition());
                        ieltsinputlayout.setVisibility(View.GONE);

                    }
                    else if (ieltsinputlayout.getVisibility()==View.GONE)
                    {
                        TransitionManager.beginDelayedTransition(ielts_card, new AutoTransition());
                        ieltsinputlayout.setVisibility(View.VISIBLE);
                    }

                }



            });


            pte_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ieltsinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);
                    otherinputlayout.setVisibility(View.GONE);



                    if (pteinputlayout.getVisibility()==View.VISIBLE)
                    {
                        TransitionManager.beginDelayedTransition(pte_card, new AutoTransition());

                        pteinputlayout.setVisibility(View.GONE);
                    }else if (pteinputlayout.getVisibility()==View.GONE)
                    {
                        TransitionManager.beginDelayedTransition(pte_card, new AutoTransition());
                        pteinputlayout.setVisibility(View.VISIBLE);
                    }


                }
            });
            toefl_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ieltsinputlayout.setVisibility(View.GONE);
                    pteinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);
                    otherinputlayout.setVisibility(View.GONE);



                    if (toeflinputlayout.getVisibility()==View.VISIBLE)
                    {
                        TransitionManager.beginDelayedTransition(toefl_card, new AutoTransition());
                        toeflinputlayout.setVisibility(View.GONE);
                    }
                    else if (toeflinputlayout.getVisibility()==View.GONE)
                    {
                        TransitionManager.beginDelayedTransition(toefl_card, new AutoTransition());
                        toeflinputlayout.setVisibility(View.VISIBLE);

                    }

                }
            });

            duoling_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ieltsinputlayout.setVisibility(View.GONE);
                    pteinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);
                    otherinputlayout.setVisibility(View.GONE);


                    if (duolinggoinputlayout.getVisibility()==View.VISIBLE)
                    {
                        TransitionManager.beginDelayedTransition(duoling_card, new AutoTransition());
                        duolinggoinputlayout.setVisibility(View.GONE);
                    }  else if (duolinggoinputlayout.getVisibility()==View.GONE)
                    {
                        TransitionManager.beginDelayedTransition(duoling_card, new AutoTransition());
                        duolinggoinputlayout.setVisibility(View.VISIBLE);
                    }

                }
            });


            othercard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ieltsinputlayout.setVisibility(View.GONE);
                    pteinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);


                    if (otherinputlayout.getVisibility()==View.VISIBLE)
                    {
                        TransitionManager.beginDelayedTransition(othercard, new AutoTransition());
                        otherinputlayout.setVisibility(View.GONE);
                    }
                    else if (otherinputlayout.getVisibility()==View.GONE)
                    {
                        TransitionManager.beginDelayedTransition(othercard, new AutoTransition());
                        otherinputlayout.setVisibility(View.VISIBLE);

                    }

                }
            });

        }


        void validation()
        {
            if(ieltsinputlayout.getVisibility()==View.VISIBLE ||pteinputlayout.getVisibility()==View.VISIBLE ||
                    duolinggoinputlayout.getVisibility()==View.VISIBLE||
                    toeflinputlayout.getVisibility()==View.VISIBLE ||otherinputlayout.getVisibility()==View.VISIBLE || checkBox.isChecked()){

                if (ieltsinputlayout.getVisibility() == View.VISIBLE) {

                    validationInputFields(reading,writing,listening,speaking,overall,readinglayout
                            ,writinglayout,listeninglayout,speakinglayout,overalllayout,"IELTS");

                }
                else if (pteinputlayout.getVisibility() == View.VISIBLE) {

                    validationInputFields(ptereading,ptewriting,ptelistening,ptespeaking,pteoverall,ptereadinglayout,ptewritinglayout,ptelisteninglayout
                            ,ptespeakinglayout,pteoveralllayout,"PTE");

                }
                else if (checkBox.isChecked()) {

                    SharedPreferences.Editor editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();

                    editor.putString("examname", "I don't hava this");
                    editor.commit();
                    onBackPressed();


                } else if (toeflinputlayout.getVisibility() == View.VISIBLE) {
                    validationInputFields(toefreading,toefwriting,toeflistening,toefspeaking,toefoverall,toefreadinglayout,toefwritinglayout,toeflisteninglayout
                            ,toefspeakinglayout,toefoveralllayout,"TOEFL");


                } else if (duolinggoinputlayout.getVisibility() == View.VISIBLE) {
                    validationInputFields(duoreading,duowriting,duolistening,duospeaking,duooverall,duoreadinglayout,duowritinglayout,duolisteninglayout
                            ,duospeakinglayout,duooveralllayout,"Duolingo ");


                }

                else if (othercard.getVisibility() == View.VISIBLE) {
                    if (!othersexaminput.getText().toString().isEmpty())
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();
                        editor.putString("examname", "Other");
                        editor.putString("other", othersexaminput.getText().toString());
                        editor.commit();
                        onBackPressed();

                    }
                    else
                    {
                        otherExam_layout.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
                        otherExam_layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
                        otherExam_layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                        otherExam_layout.setError("Required*");
                        othersexaminput.requestFocus();
                    }

                }
            }
            else {
                Toast.makeText(Preference_update_Activity.this, "Please Select Course", Toast.LENGTH_SHORT).show();
            }
        }


        void validationInputFields(TextInputEditText reading,TextInputEditText writing,
                                   TextInputEditText listening,TextInputEditText speaking,TextInputEditText overall,TextInputLayout readinglayout,
                                   TextInputLayout writinglayout, TextInputLayout listeninglayout,TextInputLayout speakinglayout,TextInputLayout overalllayout,String examname)
        {

            SharedPreferences.Editor editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();

            String readText = reading.getText().toString();
            String writeText = writing.getText().toString();
            String listenText = listening.getText().toString();
            String speakText = speaking.getText().toString();
            String overText = overall.getText().toString();


            // linear layout 1st
            if (!readText.isEmpty() && !writeText.isEmpty() &&
                    !listenText.isEmpty()&& !speakText.isEmpty() && !overText.isEmpty())
            {

                editor.putString("examname", examname);
                editor.putString("read", readText);
                editor.putString("write", writeText);
                editor.putString("listen", listenText);
                editor.putString("speak", speakText);
                editor.putString("overall", overText);
                editor.commit();
                savedata();
                onBackPressed();


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
            layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
            layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            layout.setError("Required*");
            text.requestFocus();
        }


    }
}