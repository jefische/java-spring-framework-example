package com.example.spring.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Owner {
	
	private String name;

	@Autowired
	@Qualifier("animal1") // using annotation based config to inject this animal dependency (field injection)
	private Animal animal;

	public Owner() {
	}

	public Owner(String name, Animal animal) {
		this.name = name;
		this.animal = animal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String toString() {
		return "Owner {" +
				"name='" + name + '\'' + 
				", animal='" + animal + '\'' +
				'}';
	}
}
