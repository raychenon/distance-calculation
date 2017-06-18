package com.raychenon.distancecalculation.model;

public class CalculationResultModel {

    public final String pointA;
    public final String pointB;
    public final String distance;
    public final String transportationMode;

    public CalculationResultModel(final String pointA, final String pointB, final String distance,
            final String transportationMode) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.distance = distance;
        this.transportationMode = transportationMode;
    }
}
