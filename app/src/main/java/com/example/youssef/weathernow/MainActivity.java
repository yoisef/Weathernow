package com.example.youssef.weathernow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.youssef.weathernow.models.BaseForecast;
import com.example.youssef.weathernow.models.ForcWeather;
import com.example.youssef.weathernow.models.Forecastday;
import com.example.youssef.weathernow.models.CurWeather;

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
    TextView cityy, countryy, dataa, conditionimg, conditiontext, humidity, windd, feellike, cloudd,temp;
    ImageView imgcondition,imageconditiontwo;
    RecyclerView forrecycle;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Forecastday> Data;
    foreadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data=new ArrayList<>();
        myadapter=new foreadapter(this);

        cityy = findViewById(R.id.city);
        countryy = findViewById(R.id.country);
        dataa = findViewById(R.id.data);
        conditiontext = findViewById(R.id.textcondition);
        imgcondition = findViewById(R.id.imgcondition);
        imageconditiontwo=findViewById(R.id.imgcon2);
        humidity=findViewById(R.id.humval);
        temp=findViewById(R.id.temperaturee);
        windd=findViewById(R.id.windval);
        feellike=findViewById(R.id.feelval);


        cloudd=findViewById(R.id.cloudval);
        forrecycle=findViewById(R.id.recycleforcast);
        forrecycle.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        forrecycle.setLayoutManager(mLayoutManager);


        // use a linear layout manager

         /*

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            */
      callcurrent();
      callforcast();
        forrecycle.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();







    }

    public void callforcast()
    {

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
        callf=myendpoints.getforecast("Cairo");

        callf.enqueue(new Callback<ForcWeather>() {
            @Override
            public void onResponse(Call<ForcWeather> call, Response<ForcWeather> response) {

                List<Forecastday> mylist = response.body().getForecast().getForecastday();
                foreadapter foreadapter=new foreadapter(MainActivity.this);
                foreadapter.setPhotos(mylist);



            }

            @Override
            public void onFailure(Call<ForcWeather> call, Throwable t) {

            }
        });

    }

    public  void callcurrent()
    {

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
        call = myendpoints.getcurentweather("Cairo");
        call.enqueue(new Callback<CurWeather>() {
            @Override
            public void onResponse(Call<CurWeather> call, Response<CurWeather> response) {

                cityy.setText(response.body().getLocation().getName());
                countryy.setText(response.body().getLocation().getCountry());
                dataa.setText(response.body().getLocation().getLocaltime());
                conditiontext.setText(response.body().getCurrent().getCondition().getText());
                temp.setText(String.valueOf(response.body().getCurrent().getTempC())+"°");
                humidity.setText(String.valueOf(response.body().getCurrent().getHumidity()));
                cloudd.setText(String.valueOf(response.body().getCurrent().getCloud()));
                feellike.setText(String.valueOf(response.body().getCurrent().getFeelslikeC()));
                windd.setText(String.valueOf(response.body().getCurrent().getWindKph()));


                String imgurl = response.body().getCurrent().getCondition().getIcon();
                Glide.with(MainActivity.this)
                        .load("http:" + imgurl)
                        .into(imgcondition);

                Glide.with(MainActivity.this)
                        .load("http:"+imgurl)
                        .into(imageconditiontwo);
            }




            @Override
            public void onFailure(Call<CurWeather> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    }

