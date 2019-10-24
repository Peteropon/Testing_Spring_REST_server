package com.cleancode.service;

import com.cleancode.model.Flight;

import java.util.List;

public interface IFlightService {
    List<Flight> findAll();

    Flight findFlightById(long l);
}
