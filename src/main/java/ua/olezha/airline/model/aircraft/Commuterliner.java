package ua.olezha.airline.model.aircraft;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Commuterliner extends Airplane {

    @Override
    public AircraftType getType() {
        return AircraftType.COMMUTERLINER;
    }
}
