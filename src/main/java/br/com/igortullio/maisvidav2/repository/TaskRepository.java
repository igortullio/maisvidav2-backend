package br.com.igortullio.maisvidav2.repository;

import br.com.igortullio.maisvidav2.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
