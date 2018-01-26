package ua.olezha.airline.model;

import lombok.Data;

import javax.persistence.*;

// https://marcin-chwedczuk.github.io/mapping-inheritance-in-hibernate
@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Aircraft {

    @Id
    @GeneratedValue
    private Long id;
}
