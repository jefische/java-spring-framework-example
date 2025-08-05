package com.example.spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table
public class Pie {
	
	@Id
	@Column(name = "pie_name") // to ensure snake casing in variable names?
	private String name;

	private int slices;
	private int calories;

	public Pie(){
	}

	public Pie(String name, int slices, int calories) {
		this.name = name;
		this.slices = slices;
		this.calories = calories;
	}

	public String getName() { return name; }
	public int getSlices() { return slices; }
	public int getCalories() { return calories; }

	public void setName(String name) { this.name = name; }
	public void setSlices(int slices) { this.slices = slices; }
	public void setCalories(int calories) { this.calories = calories; }
}
