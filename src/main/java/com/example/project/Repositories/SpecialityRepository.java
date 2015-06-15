package com.example.project.Repositories;

import com.example.project.Model.Specialty;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialityRepository extends PagingAndSortingRepository<Specialty, Long> {
	List<Specialty> findByNameContains(@Param("name") String name);
}
