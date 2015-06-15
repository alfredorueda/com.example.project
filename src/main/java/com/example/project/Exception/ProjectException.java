package com.example.project.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProjectException  extends RuntimeException {

	public ProjectException(Long projectId, Long developerId) {
		super("The developer_id:" + developerId + " does not master any specialty required by project_id:" + projectId);
	}

	public ProjectException(Long projId) {
		super("Project with project_id: " + projId + " doesn't exist.");
	}
}