package com.medicoApp.medicoApp.dao;

import com.medicoApp.medicoApp.entities.TestEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {
    List<TestEntity> findTestEntitiesByUser_Id(Long id, Sort sort);
    Optional<TestEntity> findByTestId(Long id);
}

