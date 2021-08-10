package br.gov.pi.sefaz.tasklist.controller.form;

import java.time.LocalDateTime;
import java.util.Date;

import br.gov.pi.sefaz.tasklist.model.Task;
import br.gov.pi.sefaz.tasklist.model.TaskStatus;
import br.gov.pi.sefaz.tasklist.repository.TaskRepository;

public class UpdateTaskForm {
	
	private String title;
	private String description;
	private Date dateConclusion;
	private TaskStatus status;
	
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}


	public Date getDateConclusion() {
		return dateConclusion;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public Task update(Long id, TaskRepository taskRepository) {
		Task task = taskRepository.getById(id);
		task.setTitle(this.title);
		task.setDescription(this.description);
		task.setDateConclusion(this.dateConclusion);
		task.setStatus(this.status);
		
		return task;
	}

}
