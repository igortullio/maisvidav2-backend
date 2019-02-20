package br.com.igortullio.maisvidav2.service;

import br.com.igortullio.maisvidav2.domain.Job;
import br.com.igortullio.maisvidav2.domain.Task;
import br.com.igortullio.maisvidav2.repository.JobRepository;
import br.com.igortullio.maisvidav2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DbService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private TaskRepository taskRepository;

    public void populaBanco() {
        Job job1 = new Job(null, "Job1", true);
        Job job2 = new Job(null, "Job2", true);
        Job job3 = new Job(null, "Job3", true);
        Job job4 = new Job(null, "Job4", false);
        Job job5 = new Job(null, "Job5", false);

        Task task1 = new Task(null, "Task1", 1.0, true);
        Task task2 = new Task(null, "Task2", 2.0, true);
        Task task3 = new Task(null, "Task3", 3.0, true);
        Task task4 = new Task(null, "Task4", 4.0, false);
        Task task5 = new Task(null, "Task5", 5.0, false);

        job1.setTasks(Arrays.asList(task1, task2, task3));
        job2.setTasks(Arrays.asList(task3, task4, task5));
        job3.setTasks(Arrays.asList(task1, task3, task5));

        task1.setJobs(Arrays.asList(job1, job3));
        task2.setJobs(Arrays.asList(job1));
        task3.setJobs(Arrays.asList(job1, job2, job3));
        task4.setJobs(Arrays.asList(job2));
        task5.setJobs(Arrays.asList(job2, job3));

        taskRepository.saveAll(Arrays.asList(task1, task2, task3, task4, task5));

        jobRepository.saveAll(Arrays.asList(job1, job2, job3, job4, job5));

        job1.setParentJob(jobRepository.findById(2).get());
        job2.setParentJob(jobRepository.findById(3).get());
        job3.setParentJob(jobRepository.findById(4).get());
        job4.setParentJob(jobRepository.findById(5).get());

        jobRepository.saveAll(Arrays.asList(job1, job2, job3, job4, job5));
    }

}
