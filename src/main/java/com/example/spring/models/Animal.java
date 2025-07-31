package com.example.spring.models;

public class Animal {

	private String species;
	private String name;
	private int age;

	public Animal() {
	}

	public Animal(String species, String name, int age) {
		this.species = species;
		this.name = name;
		this.age = age;
	}

	public String getSpecies() {
		return this.species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return "Animal {" +
				"species='" + species + '\'' + 
				", name='" + name + '\'' +
				", age='" + age + '\'' +
				'}';
	}
}
