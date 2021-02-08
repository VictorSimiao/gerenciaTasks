package br.com.victorreis.gerenciatasks.domain.task;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	Task findByDescricao(String descricao);
	
	@Override
	@Query("SELECT t FROM Task t WHERE t.appUser.username = ?#{principal}")
	Page<Task> findAll(Pageable pageable);
	
	@Override
	@Query("SELECT t FROM Task t WHERE t.id = ?1 AND t.appUser.username = ?#{principal}")
	Optional<Task> findById(Integer id);
}
