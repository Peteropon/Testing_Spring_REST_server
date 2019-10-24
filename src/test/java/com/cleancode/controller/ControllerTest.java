package com.cleancode.controller;

import com.cleancode.model.Flight;
import com.cleancode.repository.FlightRepository;
import com.cleancode.service.IFlightService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ControllerTest {

    @Test
    public void home() {
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private IFlightService flightService;

    @Test
    public void testFindFlightsGet() {
        Controller c = new Controller(flightService);
        Flight f = new Flight();
        f.setId(1L);
        when(flightRepository.findFlightById(1L)).thenReturn(f);

        Flight flight = c.findFlightById(1L);
        assertEquals(1L, flight.getId().longValue());
    }
}