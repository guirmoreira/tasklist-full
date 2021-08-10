package br.gov.pi.sefaz.tasklist.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.gov.pi.sefaz.tasklist.controller.dto.TaskDTO;
import br.gov.pi.sefaz.tasklist.controller.form.TaskForm;
import br.gov.pi.sefaz.tasklist.controller.form.UpdateTaskForm;
import br.gov.pi.sefaz.tasklist.model.Task;
import br.gov.pi.sefaz.tasklist.repository.TaskRepository;


@RestController
@RequestMapping(value="/tasks")
public class TasksController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping
	@Cacheable(value= "taskList")
	@CrossOrigin(origins = "http://localhost:8081")
	public Page<TaskDTO> lista( 
			@RequestParam int page, 
			@RequestParam int amt) {
		
		Pageable pageable = PageRequest.of(page, amt, Sort.by("id").descending());
		
		Page<Task> tasks = taskRepository.findAll(pageable);
		return TaskDTO.convert(tasks);
	}
	
	@PostMapping
	@Transactional
	@CrossOrigin(origins = "http://localhost:8081")
	@CacheEvict(value= "taskList", allEntries = true)
	public ResponseEntity<TaskDTO> create(@RequestBody @Valid TaskForm form, UriComponentsBuilder uriBuilder) {
		Task task = form.convert();
		taskRepository.save(task);
		URI uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(new TaskDTO(task));
	}
	
	
	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<TaskDTO> detail(@PathVariable Long id) {
		Optional<Task> task = taskRepository.findById(id);
		if (task.isPresent()) {
			return ResponseEntity.ok(new TaskDTO(task.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value= "taskList", allEntries = true)
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<TaskDTO> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateTaskForm form) {
		Optional<Task> optional = taskRepository.findById(id);
		if (optional.isPresent()) {
			Task task = form.update(id, taskRepository);
			return ResponseEntity.ok(new TaskDTO(task));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value= "taskList", allEntries = true)
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Task> optional = taskRepository.findById(id);
		if (optional.isPresent()) {
			taskRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
	

}
