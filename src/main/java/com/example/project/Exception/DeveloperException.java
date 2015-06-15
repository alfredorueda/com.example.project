package com.example.project.Exception;

/**
 * Created by Warren on 07/05/2015.
 */
public class DeveloperException extends RuntimeException {
	public DeveloperException(Long devId) {
		super("Developer with id: " + devId + " doesn't exist.");
	}

	public DeveloperException(Long devId, Long speId) {
		super("Dev_id:" + devId + "already have specialty_id:" + speId + ". ");
	}
}
