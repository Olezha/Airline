package ua.olezha.airline.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class AirlineCompany {

    @Id
    @GeneratedValue
    private Long id;

    private List<Aircraft> aircraftList;
}
