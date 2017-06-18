package com.raychenon.distancecalculation.http;

import com.raychenon.distancecalculation.http.response.DistanceMatrixResponse;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author  Raymond Chenon
 */

public interface GoogleMapsAPI {

    // https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=erich-weinert-str140,10409,Berlin&mode=driving&destinations=invalidenstrasse+129,10115,Berlin&key=AIzaSyC1ZsVlRnNhu7cNBuZwV-6CErmRBlxnkUE

    @Headers("Cache-Control: max-age=1280000")
    @GET("distancematrix/json")
    Call<DistanceMatrixResponse> getDistance(@Query("origins") String origin,
            @Query("destinations") String destination,
            @Query("mode") String mode,
            @Query("units") String units,
            @Query("key") String key);
}
