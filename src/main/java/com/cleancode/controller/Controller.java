package com.cleancode.controller;

import com.cleancode.FlightNotFoundException;
import com.cleancode.model.Flight;
import com.cleancode.service.FlightBusinessLogic;
import com.cleancode.service.IFlightService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class Controller {

    private IFlightService flightService;

    public Controller(IFlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hi";
    }

    @GetMapping("/flights")
    public List<Flight> findFlights(){
        return flightService.findAll();
    }

    @GetMapping("/flights/{id}")
    public Flight findFlightById(@PathVariable Long id) {
        Flight result = flightService.findFlightById(id);
        if (result == null) {
            throw new FlightNotFoundException("Flight not found");
        } else {
            return result;
        }
    }

    @DeleteMapping("/flights/{id}")
    public void deleteFlight(@PathVariable Long id) {
        Flight result = flightService.findFlightById(id);
        if (result == null) {
            throw new FlightNotFoundException("Flight not found");
        } else {
            flightService.deleteFlightById(id);
        }
    }

    @PostMapping("/flights")
    public Flight createFlight(@Valid @RequestBody Flight newFlight) {
        flightService.create(newFlight);
        return newFlight;
    }

    @PutMapping("/flights/{id}")
    public Flight updateFlight(@RequestBody Flight newFlight, @PathVariable Long id){
        if(flightService.findById(id).isPresent()) {
            return flightService.findById(id).map(flight -> {
                flight.setStart(newFlight.getStart());
                flight.setDestination(newFlight.getDestination());
                flight.setDuration(newFlight.getDuration());
                return flightService.create(flight);
            }).orElseGet(() -> {
                newFlight.setId(id);
                return flightService.create(newFlight);
            });
        } else {
            throw new FlightNotFoundException("Flight not found");
        }
    }

    @GetMapping("/flights/from/{start}")
    public List<Flight> getFlightsFrom(@PathVariable String start) {
        FlightBusinessLogic logic = new FlightBusinessLogic(flightService);
        return logic.getFlightsFrom(start);
    }
}
