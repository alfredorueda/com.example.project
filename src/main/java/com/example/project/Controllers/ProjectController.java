package com.example.project.Controllers;

import com.example.project.Exception.ProjectException;
import com.example.project.Model.*;
import com.example.project.Repositories.DeveloperRepository;
import com.example.project.Repositories.ManagerRepository;
import com.example.project.Repositories.ProjectRepository;
import com.example.project.Repositories.SpecialityRepository;
import com.example.project.Service.ProjectService;
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

    @Autowired
    private ProjectService projectService;

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
			throw new ProjectException(idProject);

		Specialty specialty = specialityRepository.findOne(idSpeciality);

		if(specialty == null)
			throw new ProjectException(idProject);

		project.getSpecialties().add(specialty);

		projectRepository.save(project);

		return project;
	}

	@RequestMapping(value="/{idProject}/manager/{idManager}", method = POST)
	public Project addManager(@PathVariable Long idProject, @PathVariable Long idManager) {

		Project project = projectRepository.findOne(idProject);

		if(project == null)
			throw new ProjectException(idProject);

		Manager manager = managerRepository.findOne(idManager);

		if(manager == null)
			throw new ProjectException(idProject);

		project.setManager(manager);

		projectRepository.save(project);

		return project;
	}

    @RequestMapping(value="/{idProject}/developers/{idDeveloper}", method = POST)
    public Project addDeveloper(@PathVariable Long idProject, @PathVariable Long idDeveloper) {

        Project project = projectService.addDeveloper(idProject, idDeveloper);

        return project;
    }

    @RequestMapping(value="/{idProject}/developers", method = GET)
    public Set<Developer> findDevelopers(@PathVariable Long idProject) {
        Project project = projectRepository.findOne(idProject);

        if(project == null) {
            throw new ProjectException(idProject);
        }

        return project.getDevelopers();
    }

    @RequestMapping(value="/{idProject}/manager", method = GET)
    public Manager findManager(@PathVariable Long idProject) {
        Project project = projectRepository.findOne(idProject);

        if(project == null) {
            throw new ProjectException(idProject);
        }

        return project.getManager();
    }
}
