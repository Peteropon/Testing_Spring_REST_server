package com.cleancode.controller;

import com.cleancode.model.Flight;
import com.cleancode.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


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

    @Test
    public void shouldReturnFlight() throws Exception {
        Flight found = new Flight(1L, "Gothenburg", "Paris", 120);
        when(flightService.findById(1L)).thenReturn(java.util.Optional.of(found));

        mockMvc.perform(get("http://localhost:7080/flights/{id}", 1L))
                .andExpect(status().isOk());
        verify(flightService, times(1)).findById(1L);
    }

    @Test
    public void whenValidInputCreateFlightReturns201() throws Exception {
        Flight flight = new Flight(4L, "Gothenburg", "Cairo", 220);

        mockMvc.perform(post("http://localhost:7080/flights/").contentType("application/json")
        .content(objectMapper.writeValueAsBytes(flight))).andExpect(status().isCreated());
    }

    @Test
    public void whenStartIsEmptyCreateFlightReturns400() throws Exception {
        Flight flight = new Flight(4L, "", "Cairo", 60);

        mockMvc.perform(post("http://localhost:7080/flights/").contentType("application/json")
                .content(objectMapper.writeValueAsString(flight))).andExpect(status().isBadRequest());
    }

    @Test
    public void whenValidPutUpdatesFlightAndReturns200() throws Exception {
        Flight found = new Flight(1L, "Gothenburg", "Paris", 120);
        Flight newFlight = new Flight(2L, "Gothenburg", "Cairo", 160);
        when(flightService.findById(1L)).thenReturn(java.util.Optional.of(found));

        mockMvc.perform(put("http://localhost:7080/flights/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newFlight)))
                .andExpect(status().isOk());
    }
}
