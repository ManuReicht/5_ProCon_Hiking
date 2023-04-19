package data;

import lombok.Data;

@Data
public class Result {
    private String name;
    private double distance;
    private double elePos;
    private double eleNeg;
    private long analyzeTime;
}
