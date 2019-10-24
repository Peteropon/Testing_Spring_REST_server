package com.cleancode.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlightTest {

    @Test(expected = IllegalArgumentException.class)
    public void flightThrowsExceptionWithEmptyString() {
        Flight f = new Flight();
    }

}