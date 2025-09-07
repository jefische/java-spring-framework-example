package com.example.spring.controller;

import javax.naming.AuthenticationException;
import javax.swing.Spring;

import org.aspectj.util.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.models.Consumer;
import com.example.spring.services.ConsumerService;

import jakarta.annotation.PostConstruct;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {
	
	private ConsumerService consumerService;

	// Field Injection Alternative:
	// If you remove the constructor and use @Autowired on the fields instead:

	// @Autowired
	// private ConsumerService consumerService;


	// How Field Injection Works:
	// 1. Spring Uses Reflection
	// Spring creates the SocialMediaController instance using the default (no-args) constructor
	// After object creation, Spring uses Java reflection to directly access the private fields
	// It bypasses normal Java access rules and injects the dependencies directly into the fields
	// 2. Injection Process
	// 3. When Injection Happens
	// Field injection occurs after object construction
	// Dependencies are available before any @PostConstruct methods run
	// Dependencies are available before the first HTTP request is processed

	// Pros and Cons Comparison:
	// Field Injection Pros:
	// Less boilerplate code - no constructor needed
	// Cleaner looking - fewer lines of code
	// Easy to add new dependencies - just add @Autowired field
	// Field Injection Cons:
	// Harder to test - can't easily create instances in unit tests
	// Mutable dependencies - fields can be changed after injection
	// Hidden dependencies - not obvious what dependencies a class needs
	// Reflection overhead - slight performance cost
	// Circular dependency issues - harder to detect at compile time
	// Constructor Injection Pros (Your Current Approach):
	// Immutable dependencies - marked as final
	// Easy unit testing - can pass mock dependencies in constructor
	// Fail-fast - missing dependencies cause startup failure
	// Clear contracts - dependencies are explicit in constructor signature
	// No reflection needed - cleaner and more performant

	// If there's multiple constructors, autowired is used to tell spring which to use.
	// @Autowired
	public ConsumerController(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}

	@PostMapping("login")
	public ResponseEntity<Void> login(@RequestBody Consumer consumer) throws AuthenticationException {
		consumerService.login(consumer.getUsername(), consumer.getPassword());
		return ResponseEntity.noContent()
				.header("username", consumer.getUsername())
				.build();
	}

	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody Consumer consumer) {
		consumerService.register(consumer);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Successfully registered");
	}

	@PatchMapping("order")
	public ResponseEntity<String> order(@RequestParam String pieName, @RequestHeader("username") String username) {
		consumerService.order(username, pieName);
		return ResponseEntity.accepted()
				.body(username + " ordered " + pieName + " pie.");
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String handleUnauthorized(AuthenticationException ex) {
		return ex.getMessage();
	}

	@GetMapping("byLastPie/{pieName}")
	public ResponseEntity<List<Consumer>> findConsumerByLastPie(@PathVariable String pieName) {
		return ResponseEntity.ok()
				.body(consumerService.findAllConsumersByLastPie(pieName));
	}
}
