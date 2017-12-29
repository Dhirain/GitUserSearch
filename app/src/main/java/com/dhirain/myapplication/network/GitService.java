package com.dhirain.myapplication.network;


import com.dhirain.myapplication.GitRepoApp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class GitService {

    public static final String API_BASE_URL = "https://api.github.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static GitClient builder;

    public static GitClient instance() {
        return builder;
    }

    static {
        //setup cache
        httpClient.readTimeout(30000, TimeUnit.MILLISECONDS).connectTimeout(30000 , TimeUnit.MILLISECONDS);
        httpClient.addNetworkInterceptor(new CacheInterceptor());
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        httpClient.cache(setCacheSize(2 * 1024 * 1024));// 2 MB

        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build().create(GitClient.class);
    }

    private static Cache setCacheSize(int cacheSize) {
        File httpCacheDirectory = new File(GitRepoApp.singleton().getContext().getCacheDir(), "responses");
        return new Cache(httpCacheDirectory, cacheSize);
    }

}
