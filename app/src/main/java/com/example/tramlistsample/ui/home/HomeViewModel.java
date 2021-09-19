package com.example.tramlistsample.ui.home;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.tramlistsample.data.network.Responses.StopInfo;
import com.example.tramlistsample.data.repositories.HomeRepository;

public class HomeViewModel extends AndroidViewModel {

    private final HomeRepository mHomeRepository;

    public HomeViewModel(Application application) {
        super(application);

        mHomeRepository = new HomeRepository(application);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public LiveData<StopInfo> getTramList()
    {
        return mHomeRepository.getTramList();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean whetherSession1()
    {
        return mHomeRepository.whetherCallSession1();
    }
}