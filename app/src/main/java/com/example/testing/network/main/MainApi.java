package com.example.testing.network.main;

import com.example.testing.model.Features;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {

    @GET("query")
    Flowable<Features> getQuakes(
            @Query("format") String format,
            @Query("orderby") String date,
            @Query("limit") String limit,
            @Query("minmagnitude") String minMagnitude,
            @Query("maxmagnitude") String maxMagnitude
    );
}
