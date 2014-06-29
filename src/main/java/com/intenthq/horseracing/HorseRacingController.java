package com.intenthq.horseracing;

import com.intenthq.horseracing.common.BallThrow;
import com.intenthq.horseracing.common.Contestant;
import com.intenthq.horseracing.util.InputParser;
import com.intenthq.horseracing.util.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class HorseRacingController {

    @Autowired
    private InputParser inputParser;

    public static final String INPUT_ATT = "input";
    public static final String PROBLEM = "problem";
    public static final String OUTPUT_ATT = "output";

    private Logger logger = LoggerFactory.getLogger("com.intenthq.horseracing");

    @RequestMapping("/horse-racing")
    public String horseRacing(ModelMap model) {
        return "horse-racing";
    }

    @RequestMapping("/horse-racing/exercise")
    public String exercise(@RequestParam(value = "input", required = false) String input, ModelMap model) {
        try {
            if (!StringUtils.isEmpty(input)) {
                logger.debug(input);
                inputParser.buildInput(input);
                model.addAttribute(INPUT_ATT, input);

                List<Contestant> result = startRace(inputParser);

                if( result.get(0).getDistance() < 220) {
                    throw new InvalidInputException("Incomplete race.");
                }

                StringBuilder outputBuilder = new StringBuilder("Position, Lane, Horse name\n");
                int position = 1;
                for (ListIterator<Contestant> li = result.listIterator(); li.hasNext(); ) {
                    Contestant contestant = li.next();
                    outputBuilder.append(position++ + ", " + contestant.getLane() + ", " + contestant.getHorseName());
                    if (li.hasNext()) {
                        outputBuilder.append("\n");
                    }
                }
                logger.debug("Contestants after race:" + result.toString());
                model.addAttribute(OUTPUT_ATT, outputBuilder.toString());
            }
        } catch (InvalidInputException e) {
            model.addAttribute(PROBLEM, e.getMessage());
            return "problem";
        }
        return "exercise";
    }

    private List<Contestant> startRace(InputParser inputParser) {
        Map<Integer, Contestant> contestants = inputParser.getContestantList();

        // this is where the race starts :)
        List<BallThrow> ballThrows = inputParser.getBallthrowList();
        logger.debug("BallThrows:" + ballThrows.toString());
        for (BallThrow bt : ballThrows) {
            Contestant contestant = contestants.get(bt.getLaneNumber());
            if (contestant.move(bt.getYards())) {
                break;
            }

        }
        List<Contestant> result = new LinkedList<>(contestants.values());
        Collections.sort(result, new Comparator<Contestant>() {
            @Override
            public int compare(Contestant o1, Contestant o2) {
                return o2.getDistance().compareTo(o1.getDistance());
            }
        });
        // this is where the race ends :D
        return result;
    }

    public void setInputParser(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    /**
     * Handles any exception except for InvalidInputException
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleError(ModelMap model, Exception e) {
        model.addAttribute(PROBLEM, "Unexpected internal error. Please contact maintenance team." + e.getMessage());
        return "problem";
    }
}
