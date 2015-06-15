package com.example.project.Controllers;

import com.example.project.Model.Specialty;
import com.example.project.Repositories.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {

	@Autowired
	private SpecialityRepository specialityRepository;

	@RequestMapping(method = POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Specialty save(@RequestBody Specialty specialty) {
		return specialityRepository.save(specialty);
	}

	@RequestMapping(method = GET)
	public List<Specialty> findAll() {
		List<Specialty> specialities = new ArrayList<Specialty>();
		Iterator<Specialty> iterator = specialityRepository.findAll().iterator();

		while(iterator.hasNext()){
			specialities.add(iterator.next());
		}

		return specialities;
	}

}
