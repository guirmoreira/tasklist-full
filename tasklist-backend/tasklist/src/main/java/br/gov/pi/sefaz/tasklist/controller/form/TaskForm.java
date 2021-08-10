package br.gov.pi.sefaz.tasklist.controller.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.gov.pi.sefaz.tasklist.model.Task;
import br.gov.pi.sefaz.tasklist.model.TaskStatus;
import br.gov.pi.sefaz.tasklist.repository.TaskRepository;

public class TaskForm {
	
	@NotNull @NotBlank @Length(min = 1)
	private String title;
	private String description;
	private LocalDateTime dateConclusion;
	private TaskStatus status;
	
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public LocalDateTime getDateConclusion() {
		return dateConclusion;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public Task convert() {
		return new Task(title, description, dateConclusion, status);
	}

}
