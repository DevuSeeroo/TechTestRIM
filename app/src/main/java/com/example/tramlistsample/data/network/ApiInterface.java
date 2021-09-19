package com.example.tramlistsample.data.network;

import com.example.tramlistsample.data.network.Responses.StopInfo;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("get.ashx?action=forecast&stop=mar&encrypt=false")
    Call<StopInfo> fetchMarlborough();

    @POST("get.ashx?action=forecast&stop=sti&encrypt=false")
    Call<StopInfo> fetchStillorgan();

}
