package com.cleancode.service;

import com.cleancode.model.Flight;
import com.cleancode.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Flight findFlightById(Long id) {
        return repository.findFlightById(id);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteFlightById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Flight create(Flight newFlight) {
        return repository.save(newFlight);
    }
}
