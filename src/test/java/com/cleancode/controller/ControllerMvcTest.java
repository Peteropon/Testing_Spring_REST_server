package com.cleancode.controller;

import com.cleancode.FlightNotFoundException;
import com.cleancode.model.Flight;
import com.cleancode.repository.FlightRepository;
import com.cleancode.service.FlightService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
public class ControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FlightService flightService;

    @MockBean
    private FlightRepository repository;

    @Test
    public void shouldReturnFlight() throws Exception {
        Flight found = new Flight(1L, "Gothenburg", "Paris", 120);
        when(flightService.findFlightById(1)).thenReturn(found);

        mockMvc.perform(get("http://localhost:7080/flights/{id}", 1L))
                .andExpect(status().isOk());
        verify(flightService, times(1)).findFlightById(1);
    }

    @Test
    public void whenFlightNotFoundShouldReturn404() throws Exception {
        mockMvc.perform(get("http://localhost:7080/flights/{id}", 5L))
                .andExpect(status().isNotFound());
        verify(flightService, times(1)).findFlightById(5);
    }

    @Test //tests the serialisation of an object sent with a post request
    public void whenValidInputCreateFlightReturns200() throws Exception {
        Flight flight = new Flight(4L, "Gothenburg", "Cairo", 220);

        mockMvc.perform(post("http://localhost:7080/flights/").contentType("application/json")
        .content(objectMapper.writeValueAsBytes(flight))).andExpect(status().isOk());
    }

    @Test
    public void whenStartIsEmptyCreateFlightReturns400() throws Exception {
        Flight flight = new Flight(4L, "", "Cairo", 60);

        mockMvc.perform(post("http://localhost:7080/flights/").contentType("application/json")
                .content(objectMapper.writeValueAsBytes(flight))).andExpect(status().isBadRequest());
    }

    @Test
    public void whenFlightNotFoundDeleteReturns404() throws Exception {
        mockMvc.perform(delete("http://localhost:7080/flights/{id}", 5L))
                .andExpect(status().isNotFound());
    }
}
