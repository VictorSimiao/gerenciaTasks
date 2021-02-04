package br.com.victorreis.gerenciatasks.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.victorreis.gerenciatasks.domain.task.Task;
import br.com.victorreis.gerenciatasks.domain.task.TaskRepository;
import br.com.victorreis.gerenciatasks.domain.user.AppUser;
import br.com.victorreis.gerenciatasks.domain.user.AppUserRepository;

@Component
public class InsertTestData {
	
	private TaskRepository taskRepository;
	private AppUserRepository appUSerRepository;
	
	@Autowired
	public InsertTestData(TaskRepository taskRepository, AppUserRepository appUSerRepository) {
		this.taskRepository = taskRepository;
		this.appUSerRepository = appUSerRepository;
	}
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		AppUser appUser1 = new AppUser("john", "abc", "John Coder");
		appUSerRepository.save(appUser1);
		
		AppUser appUser2 = new AppUser("paul", "cba", "Paul JDev");
		appUSerRepository.save(appUser2);
		
		LocalDate baseDate = LocalDate.parse("2025-02-01");
		
		for (int i = 1; i <= 5; i++) {
			Task task = new Task(String.format("Tarefa do %s #%d", appUser1.getUsername(), i), baseDate.plusDays(i), false);
			task.setAppUser(appUser1);
			taskRepository.save(task);
		}
		
		for (int i = 1; i <= 5; i++) {
			Task task = new Task(String.format("Tarefa do %s #%d", appUser2.getUsername(), i), baseDate.plusDays(i), false);
			task.setAppUser(appUser2);
			taskRepository.save(task);
		}
	}
}