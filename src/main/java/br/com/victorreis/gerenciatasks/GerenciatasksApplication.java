package br.com.victorreis.gerenciatasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.victorreis.gerenciatasks.domain.task.Task;

@SpringBootApplication
public class GerenciatasksApplication  implements RepositoryRestConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(GerenciatasksApplication.class, args);
	}
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Task.class);
		config.getCorsRegistry()
		.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("GET","POST","PUT","DELETE");
	}

	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		Validator validator = validator();
		validatingListener.addValidator("beforeCreate", validator);
		validatingListener.addValidator("beforeSave", validator);

	}
}
