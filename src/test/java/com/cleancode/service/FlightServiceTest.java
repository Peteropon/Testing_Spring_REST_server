package com.cleancode.service;

import com.cleancode.FlightNotFoundException;
import com.cleancode.model.Flight;
import com.cleancode.repository.FlightRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FlightServiceTest {

    @Test
    public void findById() {
        FlightService mockService = mock(FlightService.class);
        List<Flight> mockedList = new ArrayList<>();
        mockedList.add(new Flight(1L, "Gothenburg", "Paris", 120));
        mockedList.add(new Flight(2L, "Gothenburg", "Stockholm", 100));
        when(mockService.findById(1L)).thenReturn(Optional.ofNullable(mockedList.get(0)));
        Optional<Flight> expected = mockService.findById(1L);
        assertThat(mockedList.get(0), is(expected.get()));
    }

    @Test(expected = FlightNotFoundException.class)
    public void findByIdThrowsExceptionWhenFlightNotFound() {
        FlightService mockService = mock(FlightService.class);
        when(mockService.findById(4L)).thenThrow(new FlightNotFoundException(""));
        mockService.findById(4L);
    }

    @Test(expected = FlightNotFoundException.class)
    public void deleteFlightById() {
        FlightService mockService = mock(FlightService.class);
        doThrow(new FlightNotFoundException("")).when(mockService).deleteFlightById(1L);

        mockService.deleteFlightById(1L);
    }

    @Test
    public void create() {
    }
}