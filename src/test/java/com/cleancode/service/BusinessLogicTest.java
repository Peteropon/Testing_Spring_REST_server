package com.cleancode.service;

import com.cleancode.model.Flight;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BusinessLogicTest {

    @Test
    public void getFlightsFromGothenburgTest() {
        FlightService mockService = mock(FlightService.class);
        List<Flight> mockList = new ArrayList<>();
        mockList.add(new Flight(1L, "Gothenburg", "Paris", 120));
        mockList.add(new Flight(2L, "Gothenburg", "Stockholm", 50));
        mockList.add(new Flight(3L, "Singapore", "Stockholm", 450));
        mockList.add(new Flight(4L, "Gothenburg", "Cairo", 220));
        mockList.add(new Flight(5L, "Minsk", "Berlin", 50));
        mockList.add(new Flight(6L, "Krakow", "Gothenburg", 50));
        when(mockService.findAll()).thenReturn(mockList);

        FlightBusinessLogic mockLogic = new FlightBusinessLogic(mockService);
        List<Flight> expectedList = new ArrayList<>();
        expectedList.add(new Flight(1L, "Gothenburg", "Paris", 120));
        expectedList.add(new Flight(2L, "Gothenburg", "Stockholm", 50));
        expectedList.add(new Flight(4L, "Gothenburg", "Cairo", 220));
        List<Flight> actualList = mockLogic.getFlightsFrom("Gothenburg");
        for (int i = 0; i < expectedList.size(); i++) {
            Flight expectedFlight = expectedList.get(i);
            assertThat(expectedFlight, is(actualList.get(i)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void getFlightFromShouldThrowExceptionWhenStartIsEmpty() {
        FlightService mockService = mock(FlightService.class);
        FlightBusinessLogic mockLogic = new FlightBusinessLogic(mockService);
        mockLogic.getFlightsFrom("");
    }

    @Test
    public void getFlightsFromShouldReturnEmptyListWhenThereIsNoSuchFlight() {
        FlightService mockService = mock(FlightService.class);
        FlightBusinessLogic mockLogic = new FlightBusinessLogic(mockService);
        List<Flight> mockList = new ArrayList<>();
        mockList.add(new Flight(4L, "Gothenburg", "Cairo", 220));
        mockList.add(new Flight(5L, "Minsk", "Berlin", 50));
        mockList.add(new Flight(6L, "Krakow", "Gothenburg", 50));
        when(mockService.findAll()).thenReturn(mockList);
        List<Flight> expectedList = mockLogic.getFlightsFrom("Helsinki");
        assertTrue("Expected an empty list but list is not empty", expectedList.isEmpty());
    }

}
