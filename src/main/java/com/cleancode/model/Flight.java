package com.cleancode.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start")
    private String start;

    @Column(name = "destination")
    private String destination;

    @Column(name = "duration")
    private int duration;

    public Flight(Long id, String start, String destination, int duration) {
        this.id = id;
        this.start = start;
        this.destination = destination;
        this.duration = duration;
    }

    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return duration == flight.duration &&
                Objects.equals(id, flight.id) &&
                Objects.equals(start, flight.start) &&
                Objects.equals(destination, flight.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, destination, duration);
    }
}
