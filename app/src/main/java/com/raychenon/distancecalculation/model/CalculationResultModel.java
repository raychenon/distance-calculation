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

    @Override
    public boolean equals(final Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof CalculationResultModel)) {
            return false;
        }

        CalculationResultModel model = (CalculationResultModel) other;

        return model.pointA.equals(pointA) && model.transportationMode.equals(transportationMode)
                && model.pointB.equals(pointB) && model.distance.equals(distance);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + pointA.hashCode();
        result = 31 * result + pointB.hashCode();
        result = 31 * result + distance.hashCode();
        result = 31 * result + transportationMode.hashCode();
        return result;
    }

}
