package com.medicoApp.medicoApp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    Long id;
    LocalDate day;
    LocalTime hour;
    Boolean booked;
    Long doctorId;
    String doctorName;
}
