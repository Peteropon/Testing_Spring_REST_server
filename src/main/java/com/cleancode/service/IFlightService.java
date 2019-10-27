package com.cleancode.service;

import com.cleancode.model.Flight;

import java.util.List;
import java.util.Optional;

public interface IFlightService {
    List<Flight> findAll();

    Flight findFlightById(Long id);

    Optional<Flight> findById(Long id);

    void deleteFlightById(Long id);

    Flight create(Flight newFlight);
}
