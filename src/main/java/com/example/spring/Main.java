package com.example.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

// import org.springframework.context.support.ClassPathXmlApplicationContext;

// import com.example.spring.config.AppConfig;
// import com.example.spring.models.MyBean;
// import com.example.spring.models.Animal;
// import com.example.spring.models.Owner;

import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

// @Configuration
@SpringBootApplication
public class Main {

	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(Main.class, args);
	}

	@Bean
	public CommandLineRunner inspectorBean(ApplicationContext applicationContext) {
		return args -> {
			System.out.printf("Inspecting the beans provided by Spring Boot in %s...", appName).println();

			String[] beans = applicationContext.getBeanDefinitionNames();
			Arrays.sort(beans);
			for (String bean : beans) {
				System.out.println(bean);
			}
			System.out.println("Ending our inspection...");
		};
	}

		////////////////////////////////////////////////////////////////////////////////////////////////////////
		// This example uses Java based configuration to create the dependencies with setter 
		// based injection. This would be inside the main method.
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		// ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // Application logic
		// MyBean myBean = context.getBean(MyBean.class);
		// System.out.println("\n------------------------------------------");
		// System.out.println("Java Based Configuration: \n");
        // myBean.doSomething();
		// myBean.setName("Frijole");
		// myBean.setValue(20);
		// System.out.println(myBean);
        
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		// This example uses the Beans.xml configuration to create the dependencies with constructur and setter 
		// based injection. This would be inside the main method.
		////////////////////////////////////////////////////////////////////////////////////////////////////////
		// ApplicationContext XMLcontext = new ClassPathXmlApplicationContext("Beans.xml");

		// Animal animal1 = (Animal) XMLcontext.getBean("animal1"); // getBean returns a reguarl Object, must cast to desired type
		// Animal animal2 = (Animal) XMLcontext.getBean("animal2");

		// System.out.println("\n------------------------------------------");
		// System.out.println("XML Based Configuration: \n");
		// System.out.println(animal1);
		// System.out.println(animal2);

		// You can call this class directly if XML config only has 1 Owner type declared
		// Owner owner = XMLcontext.getBean(Owner.class); 
		// System.out.println(owner);

}