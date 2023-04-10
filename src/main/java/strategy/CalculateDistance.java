package strategy;

import data.TrackingPoint;
import data.Trk;

public class CalculateDistance implements Strategy {

    private static final double R = 6371e3; // Radius of the earth

    @Override
    public float[] doOperation(Trk trk) {
        float[] result = new float[1];
        float distance = 0;
        for (int i = 0; i < trk.getTrackingPoints().size() - 1; i++) {
            TrackingPoint tp1 = trk.getTrackingPoints().get(i);
            TrackingPoint tp2 = trk.getTrackingPoints().get(i + 1);
            double lat1 = Math.toRadians(tp1.getLat());
            double lat2 = Math.toRadians(tp2.getLat());
            double deltaLat = Math.toRadians(tp2.getLat() - tp1.getLat());
            double deltaLon = Math.toRadians(tp2.getLon() - tp1.getLon());

            double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                    Math.cos(lat1) * Math.cos(lat2) *
                            Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            distance += (float) (R * c);
        }
        result[0] = distance;
        return result;
    }
}
