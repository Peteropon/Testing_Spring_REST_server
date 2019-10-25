package com.cleancode.controller;

import com.cleancode.model.Flight;
import com.cleancode.repository.FlightRepository;
import com.cleancode.service.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ControllerTest {

    Flight f = new Flight();
    FlightRepository mockRepo = mock(FlightRepository.class);
    FlightService mockService = new FlightService(mockRepo);
    Controller controller = new Controller(mockService);

    @Test
    public void home() {
    }

    @Test
    public void testFindOneFlightGet() {
        f.setId(1L);
        when(mockService.findFlightById(1L)).thenReturn(f);
        Flight flight = controller.findFlightById(1L);
        assertEquals(1L, flight.getId().longValue());
    }

    @Test
    public void testFindAllFlightsGet() {
        List<Flight> mockedList = new ArrayList<>();
        mockedList.add(new Flight(1L, "Gothenburg", "Paris", 120));
        mockedList.add(new Flight(2L, "Gothenburg", "Stockholm", 100));
        when(controller.findFlights()).thenReturn(mockedList);

        List<Flight> expectedList = new ArrayList<>();
        expectedList.add(new Flight(1L, "Gothenburg", "Paris", 120));
        expectedList.add(new Flight(2L, "Gothenburg", "Stockholm", 100));
        for (int i = 0; i < expectedList.size(); i++) {
            Flight expectedFlight = expectedList.get(i);
            assertThat(expectedFlight, is(mockedList.get(i)));
        }
    }

    @Test
    public void testDeleteFlightCallsDeleteById() {
        List<Flight> mockedList = new ArrayList<>();
        mockedList.add(new Flight(1L, "Gothenburg", "Paris", 120));
        mockedList.add(new Flight(2L, "Gothenburg", "Stockholm", 100));
        when(mockService.findAll()).thenReturn(mockedList);
        controller.deleteFlight(1);

        verify(mockRepo).deleteById(1L);
    }

    @Test
    public void testPostCallsCreateFlight() {

    }
}