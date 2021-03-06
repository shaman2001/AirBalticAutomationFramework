package com.epam.AirBaltic.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Mark_Rudak on 3/1/2017.
 */
public class BookAFlightFormTest extends PreparationSteps {

    private static final String ERROR_MESSAGE = "The date of the inbound flight cannot be earlier than the date of the outbound flight. Please adjust your selection.";
    private static final String RETURN_DATE_ATTRIBUTE = "display: none;";
    private static final String ERROR_INPUT_EXCEPTION = "The number of infants can not be higher than the number of adults. Only an adult can accompany an infant.";

    @Test
    public void returnDateIsLessThanDepartureTest() {
        Assert.assertTrue(startPage
                .goToBookAFlightForm()
                .isReturnDateIncorrect(ORIGIN_AIRPORT, DESTINATION_AIRPORT, RETURN_UNVALID_DATE_DELTA,ERROR_MESSAGE),"Return date has an invalid meaning!");
    }

    @Test
    public void oneCanSeeReturnDateTest() {
        Assert.assertFalse(startPage
                .goToBookAFlightForm()
                .isOneWayTripReturnDateDisplayed(ORIGIN_AIRPORT, DESTINATION_AIRPORT,RETURN_DATE_ATTRIBUTE), "Visability of Return date is visible!");
    }

    @Test
    public void numberOfInfantsIsLessThanNumberOfAdults()
    {
        Assert.assertTrue(startPage
                .goToBookAFlightForm()
                .isNumberInfantsTicketsIncorrect(ORIGIN_AIRPORT, DESTINATION_AIRPORT,ERROR_INPUT_EXCEPTION, RETURN_DATE_DELTA),"Number of infants can't be higher than number of adults!");
    }

}
