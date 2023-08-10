package com.example.moec.JavaClass;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostDataAPI {

    Context Context;
    public PostDataAPI(android.content.Context context) {
        Context = context;
    }

    public void postData(String name, String job) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://merideanoverseas.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        DataModal modal = new DataModal(name, job);

        Call<DataModal> call = retrofitAPI.createPost(modal);

        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {

                DataModal responseFromAPI = response.body();

              //  String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getUsername() + "\n" + "Job : " + responseFromAPI.getPassword();


                Toast.makeText(Context, "result : "+responseFromAPI.getUsername(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                Toast.makeText(Context, "Error found is : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
