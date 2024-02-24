package jrrt.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
=======
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
>>>>>>> 5d8b28f1e152a0f70ea7078362fe73b6518317b2

@SpringBootApplication
@ComponentScan("jrrt")
@EntityScan("jrrt.entities")
@EnableJpaRepositories("jrrt.repositories")
public class Application 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(Application.class, args);
	}
}
