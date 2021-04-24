package com.civitasv.spider.api;

import com.civitasv.spider.api.service.AMapService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Retrofit基类
 */
public class RetrofitAMapClient {
    private static RetrofitAMapClient instance;
    private static Retrofit retrofit;
    private final static String BASE_URL = "https://restapi.amap.com/v3/";

    public static synchronized RetrofitAMapClient getInstance() {
        if (instance == null)
            return new RetrofitAMapClient();
        return instance;
    }

    private RetrofitAMapClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .callTimeout(5, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build();
    }

    public AMapService getAMapService() {
        return retrofit.create(AMapService.class);
    }
}