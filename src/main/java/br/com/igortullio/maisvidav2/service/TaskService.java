package br.com.igortullio.maisvidav2.service;

import br.com.igortullio.maisvidav2.domain.Task;
import br.com.igortullio.maisvidav2.repository.TaskRepository;
import br.com.igortullio.maisvidav2.service.exception.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task find(Integer id) {
        return taskRepository.findById(id).get();
    }

    public Task insert(Task job) {
        return taskRepository.save(job);
    }

    public void delete(Integer id) {
        try {
            taskRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível apagar uma task que possui vínculo");
        }
    }

    public Task update(Task job) {
        Task newTask = find(job.getId());
        updateData(newTask, job);
        return taskRepository.save(newTask);
    }

    private void updateData(Task newTask, Task task) {
        newTask.setName(task.getName());
        newTask.setCompleted(task.isCompleted());
        newTask.setCreatedAt(task.getCreatedAt());
        newTask.setJobs(task.getJobs());
        newTask.setWeight(task.getWeight());
    }

}
