package com.cleancode.controller;

import com.cleancode.model.Flight;
import com.cleancode.service.IFlightService;
import org.springframework.web.bind.annotation.*;

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
        return flightService.findFlightById(id);
    }
}
