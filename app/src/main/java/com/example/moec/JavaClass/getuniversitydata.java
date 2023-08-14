package com.example.moec.JavaClass;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moec.Adapters.Univerity_Course_Adapter;
import com.example.moec.ModulesClass.Univerity_Course_Module;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class getuniversitydata {

    ArrayList<Univerity_Course_Module> university_list;
    Context context;
    RecyclerView universtyRecyclerview;

    public getuniversitydata(Context context, RecyclerView universtyRecyclerview) {
        this.context = context;
        this.universtyRecyclerview = universtyRecyclerview;
        Getuniversitydata();
    }

    void Getuniversitydata() {

        String registrationURL = config.Base_url + "universitiesApiData";
        university_list=new ArrayList<>();

        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONObject obj = new JSONObject(s);
                    String status = obj.getString("success");
                    if (status.equals("true")) {

                        String baseurlimage = obj.getString("logobaseurl");
                        for (int i = 0; i <= 10; ++i) {
                            JSONArray array = obj.getJSONArray("data");
                            JSONObject jsonObject = array.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String url = jsonObject.getString("logo");
                            String total_course = jsonObject.getString("course");
                            String[] words = total_course.split(",");
                            int total = words.length;

                            university_list.add(new Univerity_Course_Module(baseurlimage + url, name, "Courses " + total));
                        }
                    } else {
                        Toast.makeText(context, "failed" + obj, Toast.LENGTH_SHORT).show();

                    }
                    universtyRecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                    Univerity_Course_Adapter univerity_course_adapter = new Univerity_Course_Adapter(context, university_list);
                    universtyRecyclerview.setAdapter(univerity_course_adapter);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(String... param) {

                try {
                    URL url = new URL(param[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    return br.readLine();
                } catch (Exception ex) {
                    return ex.getMessage();
                }
            }
        }
        registration obj = new registration();
        obj.execute(registrationURL);


    }

}
