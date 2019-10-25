package com.cleancode.service;

import com.cleancode.model.Flight;
import com.cleancode.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements IFlightService{

    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Flight> findAll() {
        return (List<Flight>) repository.findAll();
    }

    @Override
    public Flight findFlightById(long l) {
        return repository.findFlightById(l);
    }

    @Override
    public void deleteFlightById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Flight create(Flight newFlight) {
        return repository.save(newFlight);
    }
}
