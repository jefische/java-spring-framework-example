package com.example.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.models.Pie;

import java.util.List;

@Repository
public interface PieRepository extends CrudRepository<Pie, String> {

	List<Pie> findByCaloriesLessThan(int limit);
}