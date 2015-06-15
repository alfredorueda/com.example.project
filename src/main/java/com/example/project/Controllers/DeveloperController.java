package com.example.project.Controllers;

import com.example.project.Exception.DeveloperException;
import com.example.project.Exception.SpecialtyException;
import com.example.project.Model.*;
import com.example.project.Repositories.DeveloperRepository;
import com.example.project.Repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

	@Autowired
	private DeveloperRepository developerRepository;

	@Autowired
	private SpecialityRepository specialityRepository;

	@RequestMapping(method = POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Developer save(@RequestBody Developer developer) {
		return developerRepository.save(developer);
	}

    @RequestMapping(method = GET)
    public List<Developer> findAll() {
        List<Developer> developers = new ArrayList<Developer>();
        Iterator<Developer> iterator = developerRepository.findAll().iterator();

        while(iterator.hasNext()){
            developers.add(iterator.next());
        }

        return developers;
    }

	@RequestMapping(value = "/{id}",method = GET)
	public Developer getById(@PathVariable Long id){
		Developer developer = developerRepository.findOne(id);

		if(developer == null)
			throw new DeveloperException(id);

		return  developer;
	}

	@RequestMapping(value="/{idDeveloper}/specialties/{idSpeciality}", method = POST)
	public Developer addSpeciality(@PathVariable Long idDeveloper, @PathVariable Long idSpeciality) {
		Developer developer = developerRepository.findOne(idDeveloper);

		if(developer == null)
			throw new DeveloperException(idDeveloper);

		Specialty specialty = specialityRepository.findOne(idSpeciality);

		if(specialty == null)
			throw new SpecialtyException(idSpeciality);

		if(developer.getSpecialties().contains(specialty)){
			throw new DeveloperException(idDeveloper, idSpeciality);
		}

		developer.getSpecialties().add(specialty);

		developerRepository.save(developer);

		return developer;
	}
}
