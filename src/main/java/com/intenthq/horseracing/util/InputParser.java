package com.intenthq.horseracing.util;

import com.intenthq.horseracing.common.Contestant;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by iuliana on 6/26/14.
 */
@Component
public class InputParser {

    private List<Contestant> contestantList = null;

    public void buildContestantList(String input) throws InvalidInputException {
        contestantList = new LinkedList<>();
        String[] lines = input.split("\\r?\\n");
        if (lines.length <2) {
            throw new InvalidInputException("No data to parse.");
        }
        StringTokenizer st = new StringTokenizer(lines[0], ",");
        int index = 1;
        while (st.hasMoreTokens()) {
            contestantList.add(Contestant.of(index++, st.nextElement().toString()));
        }
    }

    public List<Contestant> getContestantList() {
        return contestantList;
    }
}
