package com.example.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.exceptions.ResourceNotFoundException;
import com.example.spring.models.Consumer;
import com.example.spring.models.Pie;
import com.example.spring.repositories.ConsumerRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.naming.AuthenticationException;

import java.util.ArrayList;

@Service
@Transactional
public class ConsumerService {
	
	// private List<Consumer> consumerList = new ArrayList<>();
	private ConsumerRepository consumerRepository;
	private PieService pieService;

	@Autowired
	public ConsumerService(PieService pieService, ConsumerRepository consumerRepository) { 
		this.pieService = pieService;
		this.consumerRepository = consumerRepository;
	}

	public void register(Consumer newConsumer) { 
		// consumerList.add(newConsumer); 
		consumerRepository.save(newConsumer);
	}

	public void order(String username, String pieName) {
		Consumer consumer = consumerRepository.findById(username)
							.orElseThrow(() -> new ResourceNotFoundException(username + " was not found in the list. Please check username and try again."));
		consumer.setLastPie(pieService.findPie(pieName));
		consumerRepository.save(consumer);
		// for(Consumer consumer:consumerList) {
		// 	if(consumer.getUsername().equals(username)) {
		// 		consumer.setLastPie(pieService.findPie(pieName));
		// 		return;
		// 	}
		// }
		// throw new ResourceNotFoundException(username + " was not found in the list. Please check username and try again.");
	}

	// TODO: Custom query
	public void login(String username, String password) throws AuthenticationException {
		if (consumerRepository.findByUsernameAndPassword(username, password).isEmpty()) {
        	throw new AuthenticationException("Check username and password credentials as they are invalid");
    	} else {
			consumerRepository.findByUsernameAndPassword(username, password);
		}
		// for(Consumer consumer:consumerList) {
		// 	if(consumer.getUsername().equals(username) && consumer.getPassword().equals(password)) {
		// 		return;
		// 	}
		// }
		// throw new AuthenticationException("Check username and password credentials as they are invalid");
	}

	@Transactional(readOnly = true)
	public List<Consumer> findAllConsumersByLastPie(String pieName) {
		Pie pie = pieService.findPie(pieName);
		return  (List<Consumer>) consumerRepository.findAllConsumersByLastPie(pie);
	}

}
