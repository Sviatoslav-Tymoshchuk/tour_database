package jdbc.exercise.beans;

import lombok.Data;

@Data
public class Tour {

    private int tourId;
    private int packageId;
    private String tourName;
    private String blurb;
    private String description;
    private double price;
    private String difficulty;
    private String graphic;
    private int length;
    private String region;
    private String keywords;
}
