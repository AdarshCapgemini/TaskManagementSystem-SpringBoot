package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repository.TaskRepository;

@SpringBootApplication
public class TaskManagementSystemApplication implements CommandLineRunner{
	@Autowired
	TaskRepository taskRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		taskRepository.getTasksByUserAndStatus("4","Pending").stream().forEach(System.out::println);
		
//		taskRepository.getTasksByPriorityAndStatus("High", "In Progress").stream().forEach(System.out::println);
		
	}

}

