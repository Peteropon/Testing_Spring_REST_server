package com.cleancode.controller;

import com.cleancode.model.Flight;
import com.cleancode.repository.FlightRepository;
import com.cleancode.service.FlightService;
import com.cleancode.service.IFlightService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerTest {

    @Test
    public void home() {
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private Controller c;

    @Mock
    private FlightRepository flightRepository;


    @Test
    public void testFindOneFlightGet() {
        Flight f = new Flight();
        FlightRepository mockRepo = mock(FlightRepository.class);
        FlightService mockService = new FlightService(mockRepo);
        Controller controller = new Controller(mockService);
        f.setId(1L);
        when(mockService.findFlightById(1L)).thenReturn(f);

        Flight flight = controller.findFlightById(1L);
        assertEquals(1L, flight.getId().longValue());
    }
}