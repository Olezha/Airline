package ua.olezha.airline.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Mapping class inheritance
 * (https://marcin-chwedczuk.github.io/mapping-inheritance-in-hibernate)
 */
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
