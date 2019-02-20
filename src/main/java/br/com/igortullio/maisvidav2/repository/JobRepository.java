package br.com.igortullio.maisvidav2.repository;

import br.com.igortullio.maisvidav2.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
}
