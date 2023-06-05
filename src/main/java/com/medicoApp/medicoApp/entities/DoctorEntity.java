package com.medicoApp.medicoApp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="doctors")
public class DoctorEntity {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String surname;
    @ManyToOne
    @JoinColumn(name = "spec_id")
    SpecializationEntity specialization;
}
