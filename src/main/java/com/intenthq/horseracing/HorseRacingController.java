package com.intenthq.horseracing;

import com.intenthq.horseracing.common.Contestant;
import com.intenthq.horseracing.util.InputParser;
import com.intenthq.horseracing.util.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HorseRacingController {

    @Autowired
    InputParser inputParser;

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
                logger.info(input);
                inputParser.buildContestantList(input);
                List<Contestant> contestants = inputParser.getContestantList();
                logger.info("Contestants: " + contestants);
                model.addAttribute(INPUT_ATT, input);
                model.addAttribute(OUTPUT_ATT, "Position, Lane, Horse name\n1, 1, Star\n2, 3, Cheyenne\n3, 4, Misty\n4, 5, Spirit\n5, 2, Dakota");
            }
        } catch (InvalidInputException e) {
            model.addAttribute(PROBLEM, e.getMessage());
            return "problem";
        }
        return "exercise";
    }

}
