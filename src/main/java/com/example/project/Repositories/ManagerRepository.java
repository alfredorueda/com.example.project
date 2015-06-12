package com.example.project.Repositories;

import com.example.project.Model.Manager;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends PagingAndSortingRepository<Manager, Long> {
	List<Manager> findByNameContains(@Param("name") String name);
}
