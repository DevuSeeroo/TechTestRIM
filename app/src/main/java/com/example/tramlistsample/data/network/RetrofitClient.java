package com.example.tramlistsample.data.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitClient {
    private static final String BaseUrl="https://luasforecasts.rpa.ie/xml/";

    private static RetrofitClient minstance;
    private final Retrofit retrofit;

    public RetrofitClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitClient getInstance(){

        if (minstance==null)
        {
            minstance=new RetrofitClient();

        }
        return minstance;
    }

    public ApiInterface getApi()
    {
        return retrofit.create(ApiInterface.class);

    }
}
