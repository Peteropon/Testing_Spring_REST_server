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
    public Flight findFlightById(@PathVariable long id) {
        Flight result = flightService.findFlightById(id);
        if (result == null) {
            throw new FlightNotFoundException("Flight not found");
        } else {
            return result;
        }
    }

    @DeleteMapping("/flights/{id}")
    public void deleteFlight(@PathVariable long id) {
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

    @GetMapping("/flights/from/{start}")
    public List<Flight> getFlightsFrom(@PathVariable String start) {
        FlightBusinessLogic logic = new FlightBusinessLogic(flightService);
        return logic.getFlightsFrom(start);
    }
}
