package com.dhirain.myapplication.network;

import com.dhirain.myapplication.model.RepoNetworkResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public interface GitClient {

    @GET("/search/users")
    Observable<RepoNetworkResponse> getRepo(
            @Query("q") String search,
            @QueryMap Map<String, String> options
    );
}
