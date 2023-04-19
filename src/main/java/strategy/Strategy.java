package strategy;

import data.Result;
import data.Trk;

public interface Strategy {
    void calculate(Trk trk, Result result);
}
