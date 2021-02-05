package br.com.victorreis.gerenciatasks.domain.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Task.class)
public class TaskRepositoryEventHandler {

	private TaskRepository taskRepository;

	@Autowired
	public TaskRepositoryEventHandler(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	@HandleBeforeCreate
	@HandleBeforeSave
	public void handle(Task task) throws TaksDuplicadaException {
		Task taskDB = taskRepository.findByDescricao(task.getDescricao());
		Boolean isDuplicada = false;

		if (taskDB != null) {
			if ((task.getId() == null || task.getId() == 0) && task.getDescricao().equals(taskDB.getDescricao())) {
				isDuplicada = true;
			}
			if (task.getId() != null && task.getId() > 0 && !task.getId().equals(taskDB.getId())) {
				isDuplicada = true;
			}
		}

		if (isDuplicada) {
			throw new TaksDuplicadaException("Já exite uma tarefa com esta descrição");
		}

	}
}
