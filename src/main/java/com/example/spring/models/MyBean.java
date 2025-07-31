package com.example.spring.models;

import org.springframework.stereotype.Component;

@Component
public class MyBean {
    // Bean implementation code
	private String beanName;
	private int value;

	public MyBean() {
	}

	public MyBean(String name, int value) {
		this.beanName = name;
		this.value = value;
	}

	public void doSomething() {
		System.out.println("Hello from my bean!");
	}

	public String getName() {
		return this.beanName;
	}

	public void setName(String name) {
		this.beanName = name;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		return "MyBean {" +
				"name='" + beanName + '\'' + 
				", value='" + value + '\'' +
				'}';
	}
}
