package com.medicoApp.medicoApp.services;

import com.medicoApp.medicoApp.dao.DoctorRepository;
import com.medicoApp.medicoApp.dao.ScheduleRepository;
import com.medicoApp.medicoApp.dao.SpecializationRepository;
import com.medicoApp.medicoApp.dao.UserRepository;
import com.medicoApp.medicoApp.dtos.Schedule;
import com.medicoApp.medicoApp.entities.DoctorEntity;
import com.medicoApp.medicoApp.entities.ScheduleEntity;
import com.medicoApp.medicoApp.entities.SpecializationEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitsService {
    DoctorRepository doctorRepository;
    SpecializationRepository specializationRepository;
    ScheduleRepository scheduleRepository;
    UserRepository userRepository;

    public VisitsService(DoctorRepository doctorRepository, SpecializationRepository specializationRepository, ScheduleRepository scheduleRepository, UserRepository userRepository){
        this.doctorRepository= doctorRepository;
        this.specializationRepository= specializationRepository;
        this.scheduleRepository= scheduleRepository;
        this.userRepository= userRepository;
    }
    public List<SpecializationEntity> showAllSpec(){
        return specializationRepository.findAll();
    }
    public List<DoctorEntity> showDocForSpec(String name){
        return doctorRepository.findBySpecialization_Name(name);
    }

    public List<Schedule> showFreeVisits(String specialist){
        List<Schedule> allFreeSchedules= new ArrayList<>();
        for (DoctorEntity doctorEntity: showDocForSpec(specialist)){
            List<Schedule> list = scheduleRepository.findByDoctor_IdAndBooked(doctorEntity.getId(), false)
                    .stream().map(s->new Schedule(s.getId(), s.getDay(), s.getHour(), s.getBooked(), s.getId(),
                            s.getDoctor().getName()+" "+s.getDoctor().getSurname())).collect(Collectors.toList());
            for (Schedule schedule: list){
                if (!schedule.getDay().isBefore(LocalDate.now())) {
                    allFreeSchedules.add(schedule);
                }
            }
        }
        Comparator<Schedule> comparator= new Comparator<Schedule>() {
            @Override
            public int compare(Schedule s1, Schedule s2) {
                return s1.getDay().isAfter(s2.getDay())? 1 : s1.getDay().isBefore(s2.getDay()) ? -1: s1.getHour().compareTo(s2.getHour());
            }
        };
        Collections.sort(allFreeSchedules, comparator);
        return allFreeSchedules;
    }
    public void book(Long userId, Long visitId){
        ScheduleEntity sEntity= scheduleRepository.findById(visitId).get();
        sEntity.setBooked(true);
        sEntity.setUser(userRepository.findById(userId).get());

        scheduleRepository.save(sEntity);
    }

    public List<Schedule> getNearestVisit(Long userId){
        return  scheduleRepository.findAllByUser_Id(userId).stream()
                .map(entity->new Schedule(entity.getId(),
                        entity.getDay(), entity.getHour(), entity.getBooked(), entity.getDoctor().getId(),
                        entity.getDoctor().getName()+" "+entity.getDoctor().getSurname())).collect(Collectors.toList());
    }
}
