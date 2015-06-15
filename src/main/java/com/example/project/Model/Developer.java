package com.example.project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Developer extends Employee {

	@Column
	@Enumerated(EnumType.STRING)
	private Category category;

	@JsonIgnore
	@ManyToMany(mappedBy = "developers")
	private Set<Project> projects = new HashSet<>();

	@JsonIgnore
	@ManyToMany
	private Set<Specialty> specialties = new HashSet<>();

	public Developer(String name, String idCard, EmployeeStatus employeeStatus,Category category) {
		super(name, idCard, employeeStatus);
		this.category = category;
	}

	public Developer() {

	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Specialty> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(Set<Specialty> specialties) {
		this.specialties = specialties;
	}



	@Override
	public String toString() {
		return "Developer{" +
				"category='" + category + '\'' +
				'}';
	}
}
