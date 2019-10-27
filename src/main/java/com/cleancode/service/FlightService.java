package com.cleancode.service;

import com.cleancode.FlightNotFoundException;
import com.cleancode.InvalidInputException;
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
        if (id > 0) return repository.findFlightById(id);
        else throw new InvalidInputException("Your input is invalid.");
    }

    @Override
    public Optional<Flight> findById(Long id) {
        Optional<Flight> result = repository.findById(id);
        if(id > 0 && result.isPresent()) return result;
        else if(id > 0) throw new FlightNotFoundException("Flight not found");
        else throw new InvalidInputException("Your input is invalid.");
    }

    @Override
    public void deleteFlightById(Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
        else throw new FlightNotFoundException("Flight not found");
    }

    @Override
    public Flight create(Flight newFlight) {
        if(repository.findById(newFlight.getId()).isPresent()) throw new InvalidInputException("The flight already exists.");
        else return repository.save(newFlight);
    }
}
