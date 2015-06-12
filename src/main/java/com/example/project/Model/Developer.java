package com.example.project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Developer extends Empleado{

	@Column
	private String category;

	@JsonIgnore
	@ManyToMany(mappedBy = "developers")
	private Set<Project> projects = new HashSet<>();

	@JsonIgnore
	@ManyToMany
	private Set<Speciality> specialities = new HashSet<>();

	public Developer(String name, int idCard, String category) {
		super(name, idCard);
		this.category = category;
	}

	public Developer() {

	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<Speciality> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(Set<Speciality> specialities) {
		this.specialities = specialities;
	}

	@Override
	public String toString() {
		return "Developer{" +
				"category='" + category + '\'' +
				'}';
	}
}
