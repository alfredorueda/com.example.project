package com.example.project.Controllers;

import com.example.project.Model.Speciality;
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
@RequestMapping("/specialities")
public class SpecialityController {

	@Autowired
	private SpecialityRepository specialityRepository;

	@RequestMapping(method = POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Speciality save(@RequestBody Speciality speciality) {
		return specialityRepository.save(speciality);
	}

	@RequestMapping(method = GET)
	public List<Speciality> findAll() {
		List<Speciality> specialities = new ArrayList<Speciality>();
		Iterator<Speciality> iterator = specialityRepository.findAll().iterator();

		while(iterator.hasNext()){
			specialities.add(iterator.next());
		}

		return specialities;
	}

}
