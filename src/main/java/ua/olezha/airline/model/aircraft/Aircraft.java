package ua.olezha.airline.model.aircraft;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Integer seatingCapacity;

    private Integer carryingCapacityKg;

    private Integer flightRangeKm;

    private Integer fuelConsumptionLitersPerHour;
}
