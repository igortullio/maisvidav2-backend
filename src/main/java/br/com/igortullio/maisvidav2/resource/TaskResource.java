package br.com.igortullio.maisvidav2.resource;

import br.com.igortullio.maisvidav2.domain.Task;
import br.com.igortullio.maisvidav2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskResource {

    @Autowired
    private TaskService taskService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Task>> findAll(){
        return ResponseEntity.ok().body(taskService.findAll());
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> find(@PathVariable Integer id){
        return ResponseEntity.ok().body(taskService.find(id));
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Task task){
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(new Date(System.currentTimeMillis()));
        }
        Task t = taskService.insert(task);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(t.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody Task task, @PathVariable Integer id){
        task.setId(id);
        taskService.update(task);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
