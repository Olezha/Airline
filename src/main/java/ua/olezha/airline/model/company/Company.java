package ua.olezha.airline.model.company;

import lombok.Data;
import ua.olezha.airline.model.aircraft.Aircraft;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "aircraft_id", referencedColumnName = "id"))
    private List<Aircraft> aircraftList = new ArrayList<>();
}
