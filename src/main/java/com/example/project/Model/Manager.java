package com.example.project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Manager extends Employee {

	@Column
	private Double bonusSuccess;

	@JsonIgnore
	@OneToMany(mappedBy = "manager")
	private Set<Project> projects = new HashSet<>();

	public Manager() {
	}

	public Manager(String name, String idCard, EmployeeStatus employeeStatus,Double bonusSuccess) {
		super(name, idCard, employeeStatus);
        this.bonusSuccess = bonusSuccess;
	}

	public Double getBonusSuccess() {
		return bonusSuccess;
	}

	public void setBonusSuccess(Double bonusSuccess) {
		this.bonusSuccess = bonusSuccess;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
}
