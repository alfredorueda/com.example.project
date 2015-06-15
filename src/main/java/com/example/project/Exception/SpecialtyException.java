package com.example.project.Exception;

/**
 * Created by Warren on 07/05/2015.
 */
public class SpecialtyException extends RuntimeException {
	public SpecialtyException(Long speId) {
		super("Specialty with specialty_id: " + speId + " doesn't exist. ");
	}
}
