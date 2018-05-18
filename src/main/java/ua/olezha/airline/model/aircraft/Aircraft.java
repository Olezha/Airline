package ua.olezha.airline.model.aircraft;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    private Integer seatingCapacity;

    private Integer carryingCapacityKg;

    private Integer flightRangeKm;

    private Integer fuelConsumptionLitersPerHour;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate created = LocalDate.now();

    public abstract AircraftType getType();
}
