package br.gov.pi.sefaz.tasklist.controller.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.domain.Page;

import br.gov.pi.sefaz.tasklist.model.Task;
import br.gov.pi.sefaz.tasklist.model.TaskStatus;

public class TaskDTO {
	
	private Long id;
	private String title;
	private String description;
	private LocalDateTime dateCreation;
	private Date dateConclusion;
	private TaskStatus status;
	
	
	public TaskDTO(Task task) {
		this.id = task.getId();
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.dateCreation = task.getDateCreation();
		this.dateConclusion = task.getDateConclusion();
		this.status = task.getStatus();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public Date getDateConclusion() {
		return dateConclusion;
	}

	public TaskStatus getStatus() {
		return status;
	}
	

	public static Page<TaskDTO> convert(Page<Task> tasks) {
		return tasks.map(TaskDTO::new);
	}

}
