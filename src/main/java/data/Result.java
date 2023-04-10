package data;

import lombok.Data;

@Data
public class Result {
    private String name;
    private float distance;
    private float elePos;
    private float eleNeg;
    private long analyzeTime;

    public Result(String name, float distance, long analyzeTime) {
        this.name = name;
        this.distance = distance;
        this.analyzeTime = analyzeTime;
    }

    public Result(String name, float elePos, float eleNeg, long analyzeTime) {
        this.name = name;
        this.elePos = elePos;
        this.eleNeg = eleNeg;
        this.analyzeTime = analyzeTime;
    }
}
