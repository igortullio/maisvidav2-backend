package br.com.igortullio.maisvidav2.resource;

import br.com.igortullio.maisvidav2.domain.Job;
import br.com.igortullio.maisvidav2.domain.Task;
import br.com.igortullio.maisvidav2.service.JobService;
import br.com.igortullio.maisvidav2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/job")
public class JobResource {

    @Autowired
    private JobService jobService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok().body(jobService.findAll());
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Job> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(jobService.find(id));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Job job){
        Job j = jobService.insert(job);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(j.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Job job, @PathVariable Integer id){
        job.setId(id);
        jobService.update(job);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
