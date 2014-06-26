package com.intenthq.horseracing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HorseRacingControllerTest {
    private static final String SOME_INPUT = "input";
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
    private static final String SAMPLE_OUTPUT = "Position, Lane, Horse name\n" +
            "1, 1, Star\n" +
            "2, 3, Cheyenne\n" +
            "3, 4, Misty\n" +
            "4, 5, Spirit\n" +
            "5, 2, Dakota";

    private ModelMap model = new ModelMap();

    private HorseRacingController horseRacingController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        horseRacingController = new HorseRacingController();
    }

    @Test
    public void horseRacingShouldReturnTheViewContainingTheWordingOfTheExercise() throws Exception {
        final String actual = horseRacingController.horseRacing(model);
        assertThat(actual, is("horse-racing"));
    }

    @Test
    public void exerciseShouldReturnTheExerciseView() throws Exception {
        final String actual = horseRacingController.exercise(SOME_INPUT, model);
        assertThat(actual, is("exercise"));
    }

    @Test
    public void exerciseWithNoInputShouldNotReturnOutput() throws Exception {
        horseRacingController.exercise(null, model);
        assertThat(model.containsAttribute(HorseRacingController.OUTPUT_ATT), is(false));
    }

    @Test
    public void exerciseWithInputShouldReturnSomeOutput() throws Exception {
        horseRacingController.exercise(SOME_INPUT, model);
        assertThat(model.containsAttribute(HorseRacingController.OUTPUT_ATT), is(true));
    }

    @Test
    public void exerciseWithInputShouldAddTheInputToTheModel() throws Exception {
        horseRacingController.exercise(SOME_INPUT, model);
        final String input = (String) model.get(HorseRacingController.INPUT_ATT);
        assertThat(input, is(SOME_INPUT));
    }

    @Test
    public void exerciseWithSampleInputShouldReturnSampleOutput() throws Exception {
        horseRacingController.exercise(SAMPLE_INPUT, model);
        final String output = (String) model.get(HorseRacingController.OUTPUT_ATT);
        assertThat(output, is(SAMPLE_OUTPUT));
    }
}
