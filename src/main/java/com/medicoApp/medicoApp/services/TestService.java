package com.medicoApp.medicoApp.services;

import com.medicoApp.medicoApp.dao.TestRepository;
import com.medicoApp.medicoApp.entities.TestEntity;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestEntity> findAllUsersTests(Long id){
        return testRepository.findTestEntitiesByUser_Id(id, Sort.by("testDate").descending());
    }
    public Optional<TestEntity> findTestDesc(Long id){
        return testRepository.findByTestId(id);
    }
}
