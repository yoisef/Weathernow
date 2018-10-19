package com.example.youssef.weathernow;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.youssef.weathernow.models.BaseForecast;
import com.example.youssef.weathernow.models.ForcWeather;
import com.example.youssef.weathernow.models.Forecastday;


import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class foreadapter extends RecyclerView.Adapter<foreadapter.viewholder> {

    Context context;
    Call<ForcWeather> callf;

    List<Forecastday> mylistfor;
    ArrayList<String> nm=new ArrayList<>();

    public foreadapter(Activity context)
    {
        this.context=context;
        mylistfor=new ArrayList<>();

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



                mylistfor = response.body().getForecast().getForecastday();
                notifyDataSetChanged();




            }

            @Override
            public void onFailure(Call<ForcWeather> call, Throwable t) {

                // Log.e("error2",t.getMessage());

            }
        });


/*
        nm.add("one");
        nm.add("two");
                nm.add("three");

*/

    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.rowrecycle,parent,false);
        viewholder vholder=new viewholder(view);
        return vholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

         holder.daydataa.setText(mylistfor.get(position).getDate());
        holder.daytextcobdition.setText(mylistfor.get(position).getDay().getCondition().getText());

        Glide.with(context)
                .load("http:"+mylistfor.get(position).getDay().getCondition().getIcon())
                .into(holder.imageView);


        holder.tempmax.setText(String.valueOf(mylistfor.get(position).getDay().getMaxtempC()));
        holder.tempmin.setText(String.valueOf(mylistfor.get(position).getDay().getMintempC()));
        holder.tempavg.setText(String.valueOf(mylistfor.get(position).getDay().getAvgtempC()));

        //holder.daydataa.setText(nm.get(position));
    }

    @Override
    public int getItemCount() {
        return mylistfor.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        TextView daydataa,daytextcobdition,tempmax,tempavg,tempmin;
        ImageView imageView;

        public viewholder(View itemView) {
            super(itemView);
            daydataa=itemView.findViewById(R.id.dataday);
            daytextcobdition=itemView.findViewById(R.id.daycondition);
            tempavg=itemView.findViewById(R.id.avgtemp);
            tempmax=itemView.findViewById(R.id.maxtemp);
            tempmin=itemView.findViewById(R.id.mintemp);
            imageView=itemView.findViewById(R.id.dayimgcondition);
        }
    }
}
