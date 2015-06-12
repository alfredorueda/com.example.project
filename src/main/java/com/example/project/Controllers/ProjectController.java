package com.example.project.Controllers;

import com.example.project.Model.*;
import com.example.project.Repositories.DeveloperRepository;
import com.example.project.Repositories.ManagerRepository;
import com.example.project.Repositories.ProjectRepository;
import com.example.project.Repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private SpecialityRepository specialityRepository;

	@Autowired
	private ManagerRepository managerRepository;

    @Autowired
    private DeveloperRepository developerRepository;

	@RequestMapping(method = POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Project save(@RequestBody Project project) {
		return projectRepository.save(project);
	}

    @RequestMapping(method = GET)
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<Project>();
        Iterator<Project> iterator = projectRepository.findAll().iterator();

        while(iterator.hasNext()){
            projects.add(iterator.next());
        }

        return projects;
    }

	@RequestMapping(value="/{idProject}/specialities/{idSpeciality}", method = POST)
	public Project addSpeciality(@PathVariable Long idProject, @PathVariable Long idSpeciality) {

		Project project = projectRepository.findOne(idProject);

		if(project == null)
			throw new ProjectException("The project id specified does not belong to any project.");

		Speciality speciality = specialityRepository.findOne(idSpeciality);

		if(speciality == null)
			throw new ProjectException("The speciality id specified does not belong to any speciality.");

		project.getSpecialities().add(speciality);

		projectRepository.save(project);

		return project;
	}

	@RequestMapping(value="/{idProject}/manager/{idManager}", method = POST)
	public Project addManager(@PathVariable Long idProject, @PathVariable Long idManager) {

		Project project = projectRepository.findOne(idProject);

		if(project == null)
			throw new ProjectException("The project id specified does not belong to any project.");

		Manager manager = managerRepository.findOne(idManager);

		if(manager == null)
			throw new ProjectException("The manager id specified does not belong to any manager.");

		project.setManager(manager);

		projectRepository.save(project);

		return project;
	}

    @RequestMapping(value="/{idProject}/developers/{idDeveloper}", method = POST)
    public Project addDeveloper(@PathVariable Long idProject, @PathVariable Long idDeveloper) {

        Project project = projectRepository.findOne(idProject);

        if(project == null)
            throw new ProjectException("The project id specified does not belong to any project.");

        Developer developer = developerRepository.findOne(idDeveloper);

        if(developer == null)
            throw new ProjectException("The developer id specified does not belong to any developer.");


        Set<Speciality> projectSpecialities = project.getSpecialities();

        boolean specialityRequired = false;
        for ( Speciality speciality : developer.getSpecialities() ) {
            if(projectSpecialities.contains(speciality))
                specialityRequired = true;
        }

        if(specialityRequired)
            project.getDevelopers().add(developer);
        else
            throw new ProjectException("The developer specified does know any required speciality for the project specified.");

        projectRepository.save(project);

        return project;
    }

    @RequestMapping(value="/{idProject}/developers", method = GET)
    public Set<Developer> findDevelopers(@PathVariable Long idProject) {
        Project project = projectRepository.findOne(idProject);

        if(project == null)
            throw new ProjectException("The project id specified does not belong to any project.");

        return project.getDevelopers();
    }

    @RequestMapping(value="/{idProject}/manager", method = GET)
    public Manager findManager(@PathVariable Long idProject) {
        Project project = projectRepository.findOne(idProject);

        if(project == null)
            throw new ProjectException("The project id specified does not belong to any project.");

        return project.getManager();
    }


}

class ProjectException extends RuntimeException {
	public ProjectException(String message){
		super(message);
	}
}
