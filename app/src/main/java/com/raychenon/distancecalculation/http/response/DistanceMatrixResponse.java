package com.raychenon.distancecalculation.http.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DistanceMatrixResponse {

    @SerializedName("destination_addresses")
    public List<String> destinationAddress;
    @SerializedName("origin_addresses")
    public List<String> originAddress;

    @SerializedName("rows")
    public List<Row> rows;

    public String status;

    private static final String ERROR = "not found";

    public boolean isValid() {
        return rows.size() > 0 && rows.get(0).elements.size() > 0;
    }

    public String getDistance() {
        return isValid() ? rows.get(0).elements.get(0).distance.text : ERROR;
    }

    public String getDuration() {
        return isValid() ? rows.get(0).elements.get(0).duration.text : ERROR;
    }
}
