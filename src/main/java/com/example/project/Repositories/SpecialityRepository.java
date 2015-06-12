package com.example.project.Repositories;

import com.example.project.Model.Speciality;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialityRepository extends PagingAndSortingRepository<Speciality, Long> {
	List<Speciality> findByCategoryContains(@Param("category") String category);
}
