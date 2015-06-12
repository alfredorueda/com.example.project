package com.example.project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Manager extends Empleado{

	@Column
	private int salaryIncrement;

	@JsonIgnore
	@OneToMany(mappedBy = "manager")
	private Set<Project> projects = new HashSet<>();

	public Manager() {
	}

	public Manager(String name, int idCard, int salaryIncrement) {
		super(name, idCard);
        this.salaryIncrement = salaryIncrement;
	}

	public int getSalaryIncrement() {
		return salaryIncrement;
	}

	public void setSalaryIncrement(int salaryIncrement) {
		this.salaryIncrement = salaryIncrement;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
}
