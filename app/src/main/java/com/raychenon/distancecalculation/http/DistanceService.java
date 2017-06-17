package com.raychenon.distancecalculation.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.raychenon.distancecalculation.http.response.DistanceMatrixResponse;
import com.raychenon.distancecalculation.params.Constants;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class DistanceService {

    static final String BASE_URL = "https://maps.googleapis.com/maps/api/";

    private GoogleMapsAPI gmAPI;

    public DistanceService() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                                                  .addConverterFactory(GsonConverterFactory.create(gson)).build();

        gmAPI = retrofit.create(GoogleMapsAPI.class);
    }

    public Call<DistanceMatrixResponse> request(final String origin, final String end, final String mode) {

        Call<DistanceMatrixResponse> call = gmAPI.getDistance(origin, end, mode, "metric", Constants.GOOGLE_API_KEY);
        Log.v("DistanceService", "origin: " + origin + " , end: " + end);
        Log.d("DistanceService", "request " + call.request().toString());
        return call;
    }

}
