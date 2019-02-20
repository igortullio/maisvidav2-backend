package br.com.igortullio.maisvidav2.service;

import br.com.igortullio.maisvidav2.domain.Job;
import br.com.igortullio.maisvidav2.repository.JobRepository;
import br.com.igortullio.maisvidav2.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job find(Integer id) {
        return jobRepository.findById(id).get();
    }

    public Job insert(Job job) {
        return jobRepository.save(job);
    }

    public Job update(Job job) {
        Job newJob = find(job.getId());
        updateData(newJob, job);
        return jobRepository.save(newJob);
    }

    public void delete(Integer id) {
        try {
            jobRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível apagar uma job que possui parentesco com outra");
        }
    }

    private void updateData(Job newJob, Job job) {
        newJob.setName(job.getName());
        newJob.setActive(job.isActive());
        newJob.setParentJob(job.getParentJob());
        newJob.setTasks(job.getTasks());
    }

}
