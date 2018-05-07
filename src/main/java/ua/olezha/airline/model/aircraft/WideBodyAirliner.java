package ua.olezha.airline.model.aircraft;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

@ToString(callSuper=true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class WideBodyAirliner extends Airplane {
    // TODO

    @Override
    public AircraftType getType() {
        return AircraftType.WIDE_BODY_AIRLINER;
    }
}
