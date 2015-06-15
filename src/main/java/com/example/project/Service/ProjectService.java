package com.example.project.Service;

import com.example.project.Exception.ProjectException;
import com.example.project.Model.Developer;
import com.example.project.Model.Project;
import com.example.project.Model.Specialty;
import com.example.project.Repositories.DeveloperRepository;
import com.example.project.Repositories.ProjectRepository;
import com.example.project.Repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * Created by alfredo on 14/06/2015.
 */
@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private SpecialityRepository specialityRepository;


    //Verifica que un developer ha de tener como minimo una de las specialties del proyecto.
    public Project addDeveloper(Long idProject, Long idDeveloper) {

        Project project = projectRepository.findOne(idProject);

        if(project == null)
            throw new ProjectException(idProject);

        Developer developer = developerRepository.findOne(idDeveloper);

        if(developer == null)
            throw new ProjectException(idProject);


        Set<Specialty> projectSpecialities = project.getSpecialties();

        boolean specialityRequired = false;

        for (Specialty specialty : developer.getSpecialties()) {
            if (projectSpecialities.contains(specialty)) {
                specialityRequired = true;
                break;
            }
        }

        if (specialityRequired) {
            project.getDevelopers().add(developer);
        } else {
            throw new ProjectException(project.getId(),developer.getId());
        }

        projectRepository.save(project);

        return project;
    }

    public void addSpecialtiesToProjects() {

        Project project = projectRepository.findOne(1L);

        Specialty spring = specialityRepository.findByNameContains("Spring").get(0);
        Specialty android = specialityRepository.findByNameContains("Android").get(0);

        project.getSpecialties().add(spring);
        project.getSpecialties().add(android);

        projectRepository.save(project);
    }

}
