package com.intenthq.horseracing.common;

/**
 * User: iuliana cosmina
 * Date: 6/29/14
 * Description: Class describing a ballthrow.
 */
public class BallThrow {
    /**
     * The lane number, range: [1-7]
     */
    private int laneNumber;

    /**
     * Number of yards, possible values = {5, 10, 20, 40, 60}
     */
    private int yards;

    public static BallThrow of(String input) {
        String[] elements = input.split(" ");
        BallThrow bt = new BallThrow();
        bt.setLaneNumber(Integer.parseInt(elements[0]));
        bt.setYards(Integer.parseInt(elements[1]));
        return bt;
    }

    public BallThrow() {
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public int getYards() {
        return yards;
    }

    public void setYards(int yards) {
        this.yards = yards;
    }

    @Override
    public String toString() {
        return "BallThrow{" +
                "laneNumber=" + laneNumber +
                ", yards=" + yards +
                '}';
    }
}
