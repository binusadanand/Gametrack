package com.igt.binu.gametrack.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by binusadanand on 25/06/2017.
 */

public class Provider {
    private static Api mApiService = null;

    public static Api getApiService() {
        if (mApiService == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient aClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://" + EndPointConstants.BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(aClient)
                    .build();
            mApiService = retrofit.create(Api.class);
        }
        return mApiService;
    }
}
