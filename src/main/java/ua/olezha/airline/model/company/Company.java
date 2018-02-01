package ua.olezha.airline.model.company;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import ua.olezha.airline.model.aircraft.Aircraft;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
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
    @Setter(AccessLevel.NONE)
    private List<Aircraft> aircraftList = new ArrayList<>();

    public void addAircraft(Aircraft aircraft) {
        aircraftList.add(aircraft);
    }

    public List<Aircraft> getAircraftList() {
        return Collections.unmodifiableList(this.aircraftList);
    }
}
