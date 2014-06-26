package com.intenthq.horseracing.common;

/**
 * Created by iuliana on 6/26/14.
 */
public class Contestant {
    private int lane;
    private String horseName;
    private int position;

    /**
     * Default Constructor
     */
    public Contestant() {
    }

    public static Contestant of(int lane, String horseName) {
        Contestant contestant = new Contestant();
        contestant.setLane(lane);
        contestant.setHorseName(horseName);
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Contestant{" +
                "lane=" + lane +
                ", horseName='" + horseName + '\'' +
                ", position=" + position +
                '}';
    }
}
