package com.sg.template;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sg.template.network.NetworkService;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppMain extends Application {

    private static Context mContext;
    private static NetworkService sNetworkService;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        mContext = getApplicationContext();
        setUpCalligraphyConfig();
        setUpRetrofit();
    }

    private void setUpCalligraphyConfig() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static NetworkService getNetworkService() {
        return sNetworkService;
    }


    public static Context getContext() {
        return mContext;
    }


    public static void setUpRetrofit() {

        sNetworkService = (NetworkService) new Retrofit.Builder()
                .client(getOkHttpClient(true))
                .baseUrl(AppConfig.getHostUrl())
                .addConverterFactory(buildGsonConverterFactory())
                .build()
                .create(NetworkService.class);

    }

    public static NetworkService getNetworkService(String baseUrl) {

        NetworkService tempNetworkClient = (NetworkService) new Retrofit.Builder()
                .client(getOkHttpClient(false))
                .baseUrl(baseUrl)
                .addConverterFactory(buildGsonConverterFactory())
                .build()
                .create(NetworkService.class);
        return tempNetworkClient;

    }

    public static SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(AppConfig.getSharedPrefName(), MODE_PRIVATE);
    }

    private static OkHttpClient getOkHttpClient(boolean isNativeClient) {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (AppConfig.isLogEnabled())
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);


        if (isNativeClient)
            return new OkHttpClient.Builder()
                    //.addInterceptor(headerInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        else
            return new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();

    }


    private static Converter.Factory buildGsonConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(Date.class, new DateFormatter());
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Gson myGson = gsonBuilder.create();
        return GsonConverterFactory.create(myGson);
    }

}