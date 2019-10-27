package com.cleancode.service;

import com.cleancode.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class FlightBusinessLogic {

    private IFlightService flightService;

    public FlightBusinessLogic(IFlightService flightService) {
        this.flightService = flightService;
    }

    public List<Flight> getFlightsFrom(String start) {
        if(start.equals("") || start.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            return flightService.findAll().stream().filter(
                    flight -> flight.getStart().equals(start)
            ).collect(Collectors.toList());
        }
    }
}
