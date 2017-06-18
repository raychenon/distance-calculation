package com.raychenon.distancecalculation.http.response;

import com.google.gson.annotations.SerializedName;

public class ElementBlock {

    @SerializedName("distance")
    Element distance;

    @SerializedName("duration")
    Element duration;

    @SerializedName("status")
    String status;
}
