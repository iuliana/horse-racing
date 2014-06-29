package com.intenthq.horseracing.util;

import com.intenthq.horseracing.common.BallThrow;
import com.intenthq.horseracing.common.Contestant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * User: iuliana cosmina
 * Date: 6/26/14
 * Description: Utility class used to process user input and build contestant and ballthrows entries.
 */
@Component
public class InputParser {

    private Map<Integer, Contestant> contestantList = null;
    private List<BallThrow> ballthrowList = null;

    private Logger logger = LoggerFactory.getLogger("com.intenthq.horseracing");

    public void buildInput(String input) throws InvalidInputException {
        contestantList = new LinkedHashMap<>();
        String[] lines = input.split("\\r?\\n");
        if (lines.length < 2) {
            throw new InvalidInputException("No data to parse.");
        }
        StringTokenizer st = new StringTokenizer(lines[0], ", ");
        int index = 1;
        while (st.hasMoreTokens()) {
            contestantList.put(index,Contestant.of(index,st.nextElement().toString()));
            index++;
        }

        ballthrowList = new LinkedList<>();
        for (int i = 1; i < lines.length; i++) {
            if (lines[i].matches("[1-7]\\s5") || lines[i].matches("[1-7]\\s[1246]0")) {
                BallThrow bt = BallThrow.of(lines[i]);
                if (bt.getLaneNumber() > contestantList.size()) {
                    logger.info("Lane " + bt.getLaneNumber() + " has no contestant. Ballthrow not usable.");
                } else {
                    ballthrowList.add(bt);
                }
            } else {
                throw new InvalidInputException("Invalid ballthrow: " + lines[i]);
            }
        }
    }

    public Map<Integer, Contestant> getContestantList() {
        return contestantList;
    }

    public List<BallThrow> getBallthrowList() {
        return ballthrowList;
    }
}
