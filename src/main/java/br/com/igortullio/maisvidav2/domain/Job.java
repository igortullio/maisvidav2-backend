package br.com.igortullio.maisvidav2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Exclude
    @Column(unique = true)
    private String name;

    @EqualsAndHashCode.Exclude
    private boolean active;

    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties({"parentJob", "tasks"})
    @OneToOne
    private Job parentJob;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "JOB_TASK", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> tasks = new ArrayList<>();

    public Job(Integer id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public void setParentJob(Job parentJob) {
        this.parentJob = parentJob.getId() != this.id ? parentJob : null;
    }

}
