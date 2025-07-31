package com.example.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.spring.config.AppConfig;
import com.example.spring.models.MyBean;
import com.example.spring.models.Animal;
import com.example.spring.models.Owner;

public class Main {
    public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Application logic
		MyBean myBean = context.getBean(MyBean.class);
        myBean.doSomething();
		myBean.setName("Frijole");
		myBean.setValue(20);
		System.out.println(myBean);
        
		// This example uses the Beans.xml configuration to create the dependencies with constructur and setter based injection
		ApplicationContext XMLcontext = new ClassPathXmlApplicationContext("Beans.xml");

		Animal animal1 = (Animal) XMLcontext.getBean("animal1"); // getBean returns a reguarl Object, must cast to desired type
		Animal animal2 = (Animal) XMLcontext.getBean("animal2");

		System.out.println("XML conext example: ");
		System.out.println(animal1);
		System.out.println(animal2);

		Owner owner = XMLcontext.getBean(Owner.class); // You can call this class directly if XML config only has 1 Owner type declared
		System.out.println(owner);
    }
}