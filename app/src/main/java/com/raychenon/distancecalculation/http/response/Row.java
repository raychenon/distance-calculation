package com.raychenon.distancecalculation.http.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("elements")
    List<ElementBlock> elements;
}
