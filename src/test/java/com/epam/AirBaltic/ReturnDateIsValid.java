package com.epam.AirBaltic;

import com.epam.AirBaltic.pages.StartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mark_Rudak on 3/1/2017.
 */
public class ReturnDateIsValid extends PageTest {

    @Test
    public void ReturnDateIsLessThanDeparture() {

        Assert.assertTrue(new StartPage(driver).openBookAndFlights().checkReturnDate(),"Something wrong");

    }

}
