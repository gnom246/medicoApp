package com.medicoApp.medicoApp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Entity
@Table(name="schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue
    Long id;
    LocalDate day;
    LocalTime hour;
    Boolean booked;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    DoctorEntity doctor;
    @OneToOne
    @JoinColumn(name = "user_id")
    UserEntity user;
}
