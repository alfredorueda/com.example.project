package com.example.project.Service;

import com.example.project.Model.Developer;
import com.example.project.Model.Specialty;
import com.example.project.Repositories.DeveloperRepository;
import com.example.project.Repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by alfredo on 15/06/2015.
 */
@Service
@Transactional
public class DeveloperService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    public void addSpecialtiesToDevelopers() {

        Developer golum = developerRepository.findByNameContains("Golum").get(0);
        Developer frodo = developerRepository.findByNameContains("Frodo").get(0);

        Specialty spring = specialityRepository.findByNameContains("Spring").get(0);
        Specialty android = specialityRepository.findByNameContains("Android").get(0);

        golum.getSpecialties().add(spring);
        frodo.getSpecialties().add(android);

        developerRepository.save(golum);
        developerRepository.save(frodo);

    }
}
