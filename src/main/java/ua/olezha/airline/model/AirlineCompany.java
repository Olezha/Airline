package ua.olezha.airline.model;

import lombok.Data;

import java.util.List;

@Data
public class AirlineCompany {

    private List<Aircraft> aircraftList;
}
