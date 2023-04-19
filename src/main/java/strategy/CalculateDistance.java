package strategy;

import data.Result;
import data.TrackingPoint;
import data.Trk;

public class CalculateDistance implements Strategy {

    private static final double R = 6371e3; // Radius of the earth in meters

    @Override
    public void calculate(Trk trk, Result result) {
        double distance = 0;
        for (int i = 0; i < trk.getTrackingPoints().size() - 1; i++) {
            TrackingPoint tp1 = trk.getTrackingPoints().get(i);
            TrackingPoint tp2 = trk.getTrackingPoints().get(i + 1);
            double phi1 = Math.toRadians(tp1.getLat());
            double phi2 = Math.toRadians(tp2.getLat());
            double deltaPhi = Math.toRadians(tp2.getLat() - tp1.getLat());
            double deltaLambda = Math.toRadians(tp2.getLon() - tp1.getLon());

            double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                    Math.cos(phi1) * Math.cos(phi2) *
                            Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            distance += (float) (R * c);
        }
        result.setDistance(distance);
    }
}
