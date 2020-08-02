package co.nitin.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * {@link SpringBootApplication} is a convenience annotation that adds all of the following:
 * * {@link Configuration}: Tags the class as a source of bean definitions for the application context.
 * * {@link EnableAutoConfiguration}: Tells Spring Boot to start adding beans based on classpath settings, 
 * 		other beans, and various property settings. For example, if spring-webmvc is on the classpath, 
 * 		this annotation flags the application as a web application and activates key behaviors, 
 * 		such as setting up a DispatcherServlet.
 * * {@link ComponentScan}: Tells Spring to look for other components, configurations, and services
 * 
 * @author weasel
 *
 */
@SpringBootApplication
public class TodoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}
}
