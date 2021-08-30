package com.vladgrin.todo.client;

import com.vladgrin.todo.client.domain.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoClientApplication {

	public static final Logger LOG = LoggerFactory.getLogger(TodoClientApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TodoClientApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Bean
	public String info() {
		return "HELLO USER!";
	}

	@Bean
	public CommandLineRunner process(ToDoRestClient client, String info) {
		return args -> {
			LOG.info(info);

			client.upsert(new ToDo("Read the AWS documentation"));
			client.upsert(new ToDo("Watch the AWS Tips youtube channel"));
			client.upsert(new ToDo("Pay the rent tomorrow"));

			Iterable<ToDo> toDos = client.findAll();
			assert toDos != null;
			toDos.forEach(toDo -> LOG.info(toDo.toString()));

			ToDo newToDO = client.upsert(new ToDo("Drink plenty of Water daily!"));
			assert newToDO != null;
			LOG.info(newToDO.toString());

			ToDo toDo = client.findBuId(newToDO.getId());
			assert toDo != null;
			LOG.info(toDo.toString());

			client.delete(newToDO.getId());
			ToDo buId = client.findBuId(newToDO.getId());
			LOG.info("" + buId);
		};
	}
}
