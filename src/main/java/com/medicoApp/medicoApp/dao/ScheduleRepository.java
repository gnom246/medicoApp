package com.medicoApp.medicoApp.dao;

import com.medicoApp.medicoApp.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository  extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findByDoctor_IdAndBooked(Long id, Boolean booked);
    Optional<ScheduleEntity> findById(Long id);
    List<ScheduleEntity> findAllByUser_Id(Long userId);
}
