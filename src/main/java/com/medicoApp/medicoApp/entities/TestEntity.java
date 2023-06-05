package com.medicoApp.medicoApp.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name="tests")
public class TestEntity {
    @Id
    @GeneratedValue
    private Long testId;
    private String testName;
    private String testDescription;
    private LocalDate testDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
