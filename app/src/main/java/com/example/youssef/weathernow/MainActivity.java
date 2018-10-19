package com.example.youssef.weathernow;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.youssef.weathernow.models.BaseForecast;
import com.example.youssef.weathernow.models.ForcWeather;
import com.example.youssef.weathernow.models.Forecastday;
import com.example.youssef.weathernow.models.CurWeather;
import com.example.youssef.weathernow.models.Searchlist;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    Call<CurWeather> call;
    Call<ForcWeather> callf;
    Call<List<Searchlist>> calllist;
    TextView cityy, countryy, dataa, conditionimg, conditiontext, humidity, windd, feellike, cloudd, temp, tgrba;
    ImageView imgcondition, imageconditiontwo;
    RecyclerView forrecycle;
    RecyclerView.LayoutManager mLayoutManager;
    List<Forecastday> Data;
    foreadapter myadapter;
    EditText autoCompleteTextView;
    List<Searchlist> mysearchlist;
    Button search;
    ArrayList<String> mytrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data = new ArrayList<>();
        mysearchlist = new ArrayList<>();
        mytrings = new ArrayList<>();

        search = findViewById(R.id.searchbutton);
        tgrba = findViewById(R.id.tgrbatext);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(loggingInterceptor);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        Endpoints myendpoints = retrofit.create(Endpoints.class);


        callf = myendpoints.getforecast("Cairo");


        callf.enqueue(new Callback<ForcWeather>() {
            @Override
            public void onResponse(Call<ForcWeather> call, Response<ForcWeather> response) {


                Data = response.body().getForecast().getForecastday();
                tgrba.setText(Data.get(0).getDate());


            }

            @Override
            public void onFailure(Call<ForcWeather> call, Throwable t) {

                // Log.e("error2",t.getMessage());

            }
        });


        cityy = findViewById(R.id.city);
        countryy = findViewById(R.id.country);
        dataa = findViewById(R.id.data);
        conditiontext = findViewById(R.id.textcondition);
        imgcondition = findViewById(R.id.imgcondition);

        imageconditiontwo = findViewById(R.id.imgcon2);
        humidity = findViewById(R.id.humval);
        temp = findViewById(R.id.temperaturee);
        windd = findViewById(R.id.windval);
        feellike = findViewById(R.id.feelval);
        autoCompleteTextView = findViewById(R.id.autocomplete);


        cloudd = findViewById(R.id.cloudval);
         forrecycle = findViewById(R.id.forcastrecycle);
       forrecycle.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
           forrecycle.setLayoutManager(mLayoutManager);
           forrecycle.setAdapter(new foreadapter(this));

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        //  callforcast();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                OkHttpClient.Builder builderr = new OkHttpClient.Builder();

                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                builderr.addInterceptor(loggingInterceptor);


                Retrofit retrofitt = new Retrofit.Builder()
                        .baseUrl("http://api.apixu.com/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(builderr.build())
                        .build();

                Endpoints myendpoints = retrofitt.create(Endpoints.class);

                call = myendpoints.getcurentweather(autoCompleteTextView.getText().toString());
                call.enqueue(new Callback<CurWeather>() {
                    @Override
                    public void onResponse(Call<CurWeather> call, Response<CurWeather> response) {


                        if (response.isSuccessful()) {


                            cityy.setText(response.body().getLocation().getName());
                            countryy.setText(response.body().getLocation().getCountry());
                            dataa.setText(response.body().getLocation().getLocaltime());
                            conditiontext.setText(response.body().getCurrent().getCondition().getText());
                            temp.setText(String.valueOf(response.body().getCurrent().getTempC()) + "°");
                            humidity.setText(String.valueOf(response.body().getCurrent().getHumidity()));
                            cloudd.setText(String.valueOf(response.body().getCurrent().getCloud()));
                            feellike.setText(String.valueOf(response.body().getCurrent().getFeelslikeC()));
                            windd.setText(String.valueOf(response.body().getCurrent().getWindKph()));


                            String imgurl = response.body().getCurrent().getCondition().getIcon();
                            Glide.with(MainActivity.this)
                                    .load("http:" + imgurl)
                                    .into(imgcondition);

                            Glide.with(MainActivity.this)
                                    .load("http:" + imgurl)
                                    .into(imageconditiontwo);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Write correct city name",Toast.LENGTH_LONG).show();
                        }

                    }


                    @Override
                    public void onFailure(Call<CurWeather> call, Throwable t) {

                        Log.d("error1", t.getMessage());


                    }
                });
            }


        });


    }
}




