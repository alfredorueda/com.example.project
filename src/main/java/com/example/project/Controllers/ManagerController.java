package com.example.project.Controllers;

import com.example.project.Repositories.ManagerRepository;
import com.example.project.Model.Manager;
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
@RequestMapping("/managers")
public class ManagerController {

	@Autowired
	private ManagerRepository managerRepository;

	@RequestMapping(method = POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Manager save(@RequestBody Manager manager) {
		return managerRepository.save(manager);
	}

	@RequestMapping(method = GET)
	public List<Manager> findAll() {
		List<Manager> managers = new ArrayList<Manager>();
		Iterator<Manager> iterator = managerRepository.findAll().iterator();

		while(iterator.hasNext()){
			managers.add(iterator.next());
		}

		return managers;
	}

}
