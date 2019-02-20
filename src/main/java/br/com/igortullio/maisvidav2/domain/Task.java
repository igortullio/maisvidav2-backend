package br.com.igortullio.maisvidav2.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    private double weight;

    @EqualsAndHashCode.Exclude
    private boolean completed;

    @EqualsAndHashCode.Exclude
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "tasks")
    private List<Job> jobs = new ArrayList<>();

    public Task(Integer id, String name, double weight, boolean completed) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.completed = completed;
        this.createdAt = new Date(System.currentTimeMillis());
    }

}
