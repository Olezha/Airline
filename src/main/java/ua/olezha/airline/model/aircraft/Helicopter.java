package ua.olezha.airline.model.aircraft;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@ToString(callSuper=true)
@Entity
public class Helicopter extends Aircraft {
}
