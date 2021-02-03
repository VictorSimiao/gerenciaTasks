package br.com.victorreis.gerenciatasks.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	Task findByDescricao(String descricao);
}
