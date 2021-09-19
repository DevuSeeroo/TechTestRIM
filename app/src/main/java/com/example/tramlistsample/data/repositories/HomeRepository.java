package com.example.tramlistsample.data.repositories;

import android.app.Application;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tramlistsample.data.network.ApiInterface;
import com.example.tramlistsample.data.network.Responses.StopInfo;
import com.example.tramlistsample.data.network.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    private final Application application;

    public HomeRepository(Application application) {

        this.application = application;//just for inserting data to sharedpreference
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public LiveData<StopInfo> getTramList()
    {

        final MutableLiveData<StopInfo> stopInfo = new MutableLiveData<>();

        Call<StopInfo> call;
        if(whetherCallSession1())
            call = RetrofitClient.getInstance().getApi().fetchMarlborough();
        else
            call =RetrofitClient.getInstance().getApi().fetchStillorgan();
        call.enqueue(new Callback<StopInfo>() {
            @Override
            public void onResponse(@NotNull Call<StopInfo> call, @NotNull Response<StopInfo> response) {
                System.out.println("api sucess");
                if(response.isSuccessful()){
                    stopInfo.setValue(response.body());
                                 }
                else
                    stopInfo.setValue(null);

                System.out.println(response);
            }

            @Override
            public void onFailure(@NotNull Call<StopInfo> call, @NotNull Throwable t) {
                System.out.println("api failure");
                System.out.println(t.toString());
                stopInfo.setValue(null);

            }
        });

        return stopInfo;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean whetherCallSession1() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        String currentTime = sdf.format(calendar.getTime());
        System.out.println("Current time "+currentTime);
        Date currentDateTime = null;
        Date session1StartTime=null;
        Date session1EndTime=null;
        Date session2StartTime=null;
        Date session2EndTime=null;
        try {
            currentDateTime = calendar.getTime();
//            currentDateTime = sdf.parse("12:01");
            session1StartTime = sdf.parse("00:00");
            session1EndTime = sdf.parse("12:00");
            session2StartTime = sdf.parse("12:01");
            session2EndTime = sdf.parse("23:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(session1StartTime!=null && session1EndTime!=null){
            if(currentDateTime.equals(session1StartTime)||currentDateTime.equals(session1EndTime)||(currentDateTime.after(session1StartTime) && currentDateTime.before(session1EndTime)))
            {
                System.out.println("current time is in between session1 start time and end time");
                return true;
            }
            else
                System.out.println("current time is not in between session1 start time and end time");

        }
        if(session2StartTime!=null && session2EndTime!=null){
            if(currentDateTime.equals(session2StartTime)||currentDateTime.equals(session2EndTime)||(currentDateTime.after(session2StartTime) && currentDateTime.before(session2EndTime)))
            {
                System.out.println("current time is in between session2 start time and end time");
                return false;
            }
            else
                System.out.println("current time is not in between session2 start time and end time");

        }
        /*Only come here when there is some issue in above condition*/
        return true;
    }
}
