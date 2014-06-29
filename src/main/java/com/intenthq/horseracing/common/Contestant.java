package com.intenthq.horseracing.common;

/**
 * User: iuliana cosmina
 * Date: 6/29/14
 * Description: Class describing a Contestant.
 */
public class Contestant {
    private int lane;
    private String horseName;
    private Integer distance;

    /**
     * Default Constructor
     */
    public Contestant() {
    }

    public static Contestant of(int lane, String horseName) {
        Contestant contestant = new Contestant();
        contestant.setLane(lane);
        contestant.setHorseName(horseName);
        contestant.setDistance(0);
        return contestant;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    public String getHorseName() {
        return horseName;
    }

    public void setHorseName(String horseName) {
        this.horseName = horseName;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean move(int distance) {
        this.distance += distance;
        return distance >= 220;
    }

    @Override
    public String toString() {
        return "Contestant{" +
                "lane=" + lane +
                ", horseName='" + horseName + '\'' +
                ", distance=" + distance +
                '}';
    }
}
