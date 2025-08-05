package com.example.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring.models.Consumer;
import com.example.spring.models.Pie;

import java.util.Optional;
import java.util.Collection;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, String> {
	
	Optional<Consumer> findByUsernameAndPassword(String username, String password);

	@Query("SELECT c FROM Consumer c where c.lastPie = ?1")
	Collection<Consumer> findAllConsumersByLastPie(Pie pie);
}
