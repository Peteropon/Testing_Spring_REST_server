package com.cleancode.controller;

import com.cleancode.model.Flight;
import com.cleancode.repository.FlightRepository;
import com.cleancode.service.FlightService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private FlightRepository repository;

    @Test
    public void shouldReturnFlight() throws Exception{
        Flight found = new Flight(1L, "Gothenburg", "Paris", 120);
        when(flightService.findFlightById(1)).thenReturn(found);

        mockMvc.perform(get("http://localhost:7080/flights/{id}", 1L)).andExpect(status().isOk());
        verify(flightService, times(1)).findFlightById(1);
    }

    @Test
    @Ignore
    public void whenFlightNotFoundShouldReturnNotFound() throws Exception {
        when(flightService.findFlightById(5)).thenThrow(new Exception());
        mockMvc.perform(get("http://localhost:7080/flights/{id}", 5L))
                .andExpect(status().isNotFound());
        verify(flightService, times(1)).findFlightById(5);
    }
}
