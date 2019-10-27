package com.cleancode.service;

import com.cleancode.InvalidInputException;
import com.cleancode.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class FlightBusinessLogic {

    private IFlightService flightService;

    public FlightBusinessLogic(IFlightService flightService) {
        this.flightService = flightService;
    }

    public List<Flight> getFlightsFrom(String start) {
        if(start.equals("") || start.isEmpty()) {
            throw new InvalidInputException("Invalid input");
        } else {
            return flightService.findAll().stream().filter(
                    flight -> flight.getStart().equals(start)
            ).collect(Collectors.toList());
        }
    }
}
