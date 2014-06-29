package com.intenthq.horseracing.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: iuliana cosmina
 * Date: 6/29/14
 * Description: Class to test the InputParser class
 */
public class InputParserTest {
    private static final String SAMPLE_INPUT = "Star, Dakota, Cheyenne, Misty, Spirit\n" +
            "1 60\n" +
            "3 5\n" +
            "1 60\n" +
            "4 5\n" +
            "4 10\n" +
            "2 5\n" +
            "5 10\n" +
            "1 60\n" +
            "3 20\n" +
            "7 10\n" +
            "1 40\n" +
            "2 60";

    private InputParser inputParser = new InputParser();


    @Test
    public void validationShouldNotFail() throws InvalidInputException {
        inputParser.buildInput(SAMPLE_INPUT);
        assertThat(inputParser.getContestantList().size(), is(5));
        assertThat(inputParser.getBallthrowList().size(), is(11));
    }

    @Test(expected = InvalidInputException.class)
    public void validationFirstLineShouldFail() throws InvalidInputException {
        inputParser.buildInput("aaaaa");
    }

    @Test(expected = InvalidInputException.class)
    public void validationNextLinesShouldFail() throws InvalidInputException {
        inputParser.buildInput("aaaaa\n" + "1 60\n" +
                "a 5\n" +
                "1 60\n");
    }
}
