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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.interest_area_Adapter;
import com.example.moec.Adapters.most_prefered_destination_Adapter;
import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.get_country_data;
import com.example.moec.JavaClass.get_subject_data;
import com.example.moec.JavaClass.update_preference;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.loginActivity.registration_Activity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class preference_Actvity extends AppCompatActivity {

    TextView index;
    StepView stepViewProgressBar;
    int stepcount = 1;

    LinearLayout page1, page2, page5;
    Button nextbutton;

    page5 page5class;
    page2 page2class;
    page1 page1class;
    TextView toolbar_title, cleartext;
    ProgressBar progressBar;


    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_actvity);


        // Fragment Layout ids show and hide

        page1 = findViewById(R.id.page1);
        page2 = findViewById(R.id.page2);
        page5 = findViewById(R.id.page5);
        progressBar = findViewById(R.id.progressBar);
        toolbar_title = findViewById(R.id.toolbar_title);
        cleartext = findViewById(R.id.cleartext);
        toolbar_title.setText("Preferences");
        cleartext.setTextColor(Color.parseColor("#000000"));
        cleartext.setText("Skip >>");
        cleartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (stepcount < 5) {
                    progressbar(++stepcount);
                }


            }
        });


        // classes Objects

        page1class = new page1();
        page2class = new page2();
        page5class = new page5();

        // page 4 i want to study in aboard radio button

        page1class.setdataonRecyclerview();
        page2class.setdataonRecyclerview();
        page5class.cardsClickAndShow_layout();


        textwatchercourse(page5class.readinglayout, page5class.reading);
        textwatchercourse(page5class.writinglayout, page5class.writing);
        textwatchercourse(page5class.listeninglayout, page5class.listening);
        textwatchercourse(page5class.speakinglayout, page5class.speaking);
        textwatchercourse(page5class.overalllayout, page5class.overall);

        textwatchercourse(page5class.ptereadinglayout, page5class.ptereading);
        textwatchercourse(page5class.ptewritinglayout, page5class.ptewriting);
        textwatchercourse(page5class.ptelisteninglayout, page5class.ptelistening);
        textwatchercourse(page5class.ptespeakinglayout, page5class.ptespeaking);
        textwatchercourse(page5class.pteoveralllayout, page5class.pteoverall);

        textwatchercourse(page5class.toefreadinglayout, page5class.toefreading);
        textwatchercourse(page5class.toefwritinglayout, page5class.toefwriting);
        textwatchercourse(page5class.toeflisteninglayout, page5class.toeflistening);
        textwatchercourse(page5class.toefspeakinglayout, page5class.toefspeaking);
        textwatchercourse(page5class.toefoveralllayout, page5class.toefoverall);

        textwatchercourse(page5class.duoreadinglayout, page5class.duoreading);
        textwatchercourse(page5class.duowritinglayout, page5class.duowriting);
        textwatchercourse(page5class.duolisteninglayout, page5class.duolistening);
        textwatchercourse(page5class.duospeakinglayout, page5class.duospeaking);
        textwatchercourse(page5class.duooveralllayout, page5class.duooverall);

        textwatchercourse(page5class.otherExam_layout, page5class.othersexaminput);


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


    void nextbottonvalidation() {
        if (page1.getVisibility() == View.VISIBLE) {


            if (page2class.checkselectcountry) {
                page2class.checkselectcountry = false;

                progressbar(++stepcount);
            } else {
                Toast.makeText(preference_Actvity.this, "Please Select Country", Toast.LENGTH_SHORT).show();
            }

        } else if (page2.getVisibility() == View.VISIBLE) {

            if (page2class.checkselectcountry) {
                page2class.checkselectcountry = false;

                progressbar(++stepcount);
            } else {

                Toast.makeText(preference_Actvity.this, "Please Select Interest", Toast.LENGTH_SHORT).show();
            }


        } else if (page5.getVisibility() == View.VISIBLE) {

            if (page5class.checkBox.isChecked()) {
                page5class.pteinputlayout.setVisibility(View.GONE);
                page5class.toeflinputlayout.setVisibility(View.GONE);
                page5class.duolinggoinputlayout.setVisibility(View.GONE);
                page5class.otherinputlayout.setVisibility(View.GONE);
                if (stepcount < 5) {
                    progressbar(++stepcount);
                }

            } else page5class.validation();

        }


    }


    void textwatchercourse(TextInputLayout layout, TextInputEditText text) {
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


    void progressbar(int stepcount) {
        index.setText(stepcount + " of 4 ");
        stepViewProgressBar.getState()
                .animationType(StepView.ANIMATION_LINE)
                .nextStepLineColor(ContextCompat.getColor(getApplicationContext(), R.color.background_blue_shadew))
                .doneStepMarkColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                .stepsNumber(4)
                .nextStepCircleEnabled(false)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(5)
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
                page5.setVisibility(View.GONE);
                break;

            case 3:
                page2.setVisibility(View.GONE);
                page5.setVisibility(View.VISIBLE);

                break;
            case 4:

                callAPimethod();
                break;
        }

    }


    void callAPimethod() {
        SharedPreferences preferences = getSharedPreferences("registrationform", Context.MODE_PRIVATE);

        String userid = preferences.getString("userid", null);
        String pre_country = preferences.getString("pre_country", null);
        String interest = preferences.getString("interest", null);
        String exam = preferences.getString("examname", null);
        String writescore = preferences.getString("write", null);
        String readscore = preferences.getString("read", null);
        String listening = preferences.getString("listen", null);
        String speaking = preferences.getString("speak", null);
        String ovarall = preferences.getString("overall", null);


        String registrationURL = config.Base_url + "setPreferenceApiData?" +
                "user_id=" +userid+
                "&des_country=" + pre_country +
                "&intrest=" + interest +
                "&qualification=" +
                "&edu_marsks=" +
                "&englishtest=" + exam +
                "&writingscore=" + writescore +
                "&listeningscore=" + readscore +
                "&readingscore=" + listening +
                "&speakingscore=" + speaking +
                "&over_allscore=" + ovarall;
        stepcount--;

        new update_preference(preference_Actvity.this,registrationURL);

    }

    @Override
    public void onBackPressed() {
        stepcount--;

        if (stepcount == 0) {
            startActivity(new Intent(preference_Actvity.this, registration_Activity.class));
            finish();
        } else if (stepcount < 5 && stepcount > 0) {


            progressbar(stepcount);

        }

    }



    // page number 1 class
    class page1 {


        RecyclerView mostpreferedRecyclerview = findViewById(R.id.mostpreferedRecyclerview);

        void setdataonRecyclerview() {


            onClickInterface onclickInterface = new onClickInterface() {
                @Override
                public void setClick(int position, String text) {
                    if (position > -1) {
                        SharedPreferences.Editor editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();
                        editor.putString("pre_country", text);
                        editor.commit();
                        page2class.checkselectcountry = true;

                    }

                }
            };


         //   new get_country_data(progressBar,getApplicationContext(),mostpreferedRecyclerview,config.Base_url+"crmcountriesApiData",onclickInterface);



        }
    }


    // page number 2 class

    class page2 {
        RecyclerView recyclerView = findViewById(R.id.interest_recyclerview);
        boolean checkselectcountry = false;
        ArrayList<module_all_program> list = new ArrayList<>();

        void setdataonRecyclerview() {

            onClickInterface onclickInterface = new onClickInterface() {
                @Override
                public void setClick(int position, String text) {
                    if (!checkselectcountry) {
                        SharedPreferences.Editor editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();
                        editor.putString("interest", text);
                        editor.commit();
                        checkselectcountry = true;
                    }

                }
            };

          //  new get_subject_data(progressBar,list,getApplicationContext(),recyclerView,config.Base_url+"crmsubjectApiData",onclickInterface);

        }

    }

    // page number 5 class
    class page5 {

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


        void cardsClickAndShow_layout() {
            ielts_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pteinputlayout.setVisibility(View.GONE);
                    toeflinputlayout.setVisibility(View.GONE);
                    duolinggoinputlayout.setVisibility(View.GONE);
                    otherinputlayout.setVisibility(View.GONE);


                    if (ieltsinputlayout.getVisibility() == View.VISIBLE) {

                        TransitionManager.beginDelayedTransition(ielts_card, new AutoTransition());
                        ieltsinputlayout.setVisibility(View.GONE);

                    } else if (ieltsinputlayout.getVisibility() == View.GONE) {
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


                    if (pteinputlayout.getVisibility() == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(pte_card, new AutoTransition());

                        pteinputlayout.setVisibility(View.GONE);
                    } else if (pteinputlayout.getVisibility() == View.GONE) {
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


                    if (toeflinputlayout.getVisibility() == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(toefl_card, new AutoTransition());
                        toeflinputlayout.setVisibility(View.GONE);
                    } else if (toeflinputlayout.getVisibility() == View.GONE) {
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


                    if (duolinggoinputlayout.getVisibility() == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(duoling_card, new AutoTransition());
                        duolinggoinputlayout.setVisibility(View.GONE);
                    } else if (duolinggoinputlayout.getVisibility() == View.GONE) {
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


                    if (otherinputlayout.getVisibility() == View.VISIBLE) {
                        TransitionManager.beginDelayedTransition(othercard, new AutoTransition());
                        otherinputlayout.setVisibility(View.GONE);
                    } else if (otherinputlayout.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(othercard, new AutoTransition());
                        otherinputlayout.setVisibility(View.VISIBLE);

                    }

                }
            });

        }


        void validation() {
            if (ieltsinputlayout.getVisibility() == View.VISIBLE || pteinputlayout.getVisibility() == View.VISIBLE ||
                    duolinggoinputlayout.getVisibility() == View.VISIBLE ||
                    toeflinputlayout.getVisibility() == View.VISIBLE || otherinputlayout.getVisibility() == View.VISIBLE) {


                if (page5class.ieltsinputlayout.getVisibility() == View.VISIBLE) {

                    validationInputFields(reading, writing, listening, speaking, overall, readinglayout
                            , writinglayout, listeninglayout, speakinglayout, overalllayout, "IELTS");

                } else if (pteinputlayout.getVisibility() == View.VISIBLE) {

                    validationInputFields(ptereading, ptewriting, ptelistening, ptespeaking, pteoverall, ptereadinglayout, ptewritinglayout, ptelisteninglayout
                            , ptespeakinglayout, pteoveralllayout, "PTE");

                } else if (toeflinputlayout.getVisibility() == View.VISIBLE) {
                    validationInputFields(toefreading, toefwriting, toeflistening, toefspeaking, toefoverall, toefreadinglayout, toefwritinglayout, toeflisteninglayout
                            , toefspeakinglayout, toefoveralllayout, "TOEFL");


                } else if (duolinggoinputlayout.getVisibility() == View.VISIBLE) {
                    validationInputFields(duoreading, duowriting, duolistening, duospeaking, duooverall, duoreadinglayout, duowritinglayout, duolisteninglayout
                            , duospeakinglayout, duooveralllayout, "Duolingo ");


                } else if (othercard.getVisibility() == View.VISIBLE) {

                    if (!othersexaminput.getText().toString().isEmpty()) {
                        SharedPreferences.Editor  editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();

                        editor.putString("examname","Other");
                        editor.commit();
                        progressbar(++stepcount);
                    } else {
                        otherExam_layout.startAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.shake_text));
                        otherExam_layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
                        otherExam_layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                        otherExam_layout.setError("Required*");
                        othersexaminput.requestFocus();
                    }

                }
            } else {
                Toast.makeText(preference_Actvity.this, "Please Select Course", Toast.LENGTH_SHORT).show();
            }
        }


        void validationInputFields(TextInputEditText reading, TextInputEditText writing,
                                   TextInputEditText listening, TextInputEditText speaking, TextInputEditText overall, TextInputLayout readinglayout,
                                   TextInputLayout writinglayout, TextInputLayout listeninglayout, TextInputLayout speakinglayout,
                                   TextInputLayout overalllayout, String examname) {

            String readText = reading.getText().toString();
            String writeText = writing.getText().toString();
            String listenText = listening.getText().toString();
            String speakText = speaking.getText().toString();
            String overText = overall.getText().toString();

            // linear layout 1st
            if (!readText.isEmpty() && !writeText.isEmpty() &&
                    !listenText.isEmpty() && !speakText.isEmpty() && !overText.isEmpty()) {

                SharedPreferences.Editor  editor = getSharedPreferences("registrationform", Context.MODE_PRIVATE).edit();

                editor.putString("examname",examname);
                editor.putString("write",writeText);
                editor.putString("read",readText);
                editor.putString("listen",listenText);
                editor.putString("speak",speakText);
                editor.putString("overall",overText);
                editor.commit();
                callAPimethod();

            } else if (readText.isEmpty()) {
                errorShowFunction(readinglayout, reading);
            } else if (writeText.isEmpty()) {
                errorShowFunction(writinglayout, writing);
            } else if (listenText.isEmpty()) {
                errorShowFunction(listeninglayout, listening);
            } else if (speakText.isEmpty()) {
                errorShowFunction(speakinglayout, speaking);
            } else if (overText.isEmpty()) {
                errorShowFunction(overalllayout, overall);
            }
        }


        void errorShowFunction(TextInputLayout layout, TextInputEditText text) {
            layout.startAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.shake_text));
            layout.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
            layout.setErrorTextColor(ColorStateList.valueOf(Color.RED));
            layout.setError("Required*");
            text.requestFocus();
        }


    }


}

