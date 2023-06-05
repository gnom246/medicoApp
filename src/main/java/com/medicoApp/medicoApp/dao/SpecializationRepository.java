package com.medicoApp.medicoApp.dao;

import com.medicoApp.medicoApp.entities.SpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecializationRepository  extends JpaRepository<SpecializationEntity, Long> {
    Optional<SpecializationEntity> findByName(String name);

    @Override
    List<SpecializationEntity> findAll();
}
