package ua.olezha.airline.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Data
@ToString(callSuper=true)
@Entity
public abstract class Airplane extends Aircraft {
}
