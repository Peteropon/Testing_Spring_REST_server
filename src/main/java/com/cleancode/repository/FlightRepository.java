package com.cleancode.repository;

import com.cleancode.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    Flight findFlightById(long l);
}
