package com.example.project.Repositories;

import com.example.project.Model.Developer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeveloperRepository extends PagingAndSortingRepository<Developer, Long> {
	List<Developer> findByNameContains(@Param("name") String name);
}
