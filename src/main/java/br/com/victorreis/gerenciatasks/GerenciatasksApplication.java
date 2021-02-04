package br.com.victorreis.gerenciatasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import br.com.victorreis.gerenciatasks.domain.task.Task;

@SpringBootApplication
public class GerenciatasksApplication  implements RepositoryRestConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(GerenciatasksApplication.class, args);
	}
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Task.class);
	}

}
