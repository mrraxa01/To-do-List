package com.mrraxa01.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mrraxa01.todolist.model.Task;
import com.mrraxa01.todolist.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TaskController {

	@Autowired
	TaskService taskService;

	@PostMapping("/tasks")
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask(@RequestBody Task task) {
		log.info("Criando uma nova tarefa com as informações [{}]", task);		
		return taskService.createTask(task);
	}
	
	@GetMapping("/tasks")
	@ResponseStatus(HttpStatus.OK)
	public List<Task> getAllTasks(){
		log.info("Listando todas as tarefas cadastradas [{}]");
		return taskService.listAllTasks();
	}

	@GetMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long id){
		log.info("Buscando a tarefa com o Id [{}]", id);
		return taskService.findTaskById(id);
	}
	
	@PutMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)		
	public ResponseEntity<Task> updateTaskById(@PathVariable(value = "id") Long id, @RequestBody Task task){
		log.info("Atualizando a tarefa com Id [{}], as novas informações são : [{}]", id, task);
		return taskService.updateTaskById(task,id);
	}
	
	@DeleteMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> deleteTaskById(@PathVariable(value = "id") Long id){
		log.info("Deletando a tarefa com Id [{}]", id);
		
		return taskService.deleteById(id);
	}
	
	
}
