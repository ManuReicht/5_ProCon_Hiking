package strategy;

import data.TrackingPoint;
import data.Trk;

public class CalculateElevation implements Strategy {

    @Override
    public float[] doOperation(Trk trk) {
        float[] result = new float[2];
        float elePos = 0;
        float eleNeg = 0;
        for (int i = 0; i < trk.getTrackingPoints().size() - 1; i++) {
            TrackingPoint tp1 = trk.getTrackingPoints().get(i);
            TrackingPoint tp2 = trk.getTrackingPoints().get(i + 1);
            if (tp2.getEle() > tp1.getEle()) {
                elePos += tp2.getEle() - tp1.getEle();
            } else {
                eleNeg += tp1.getEle() - tp2.getEle();
            }
        }
        result[0] = elePos;
        result[1] = eleNeg;
        return result;
    }
}
