package br.com.restful.projetorestful.rest;

import br.com.restful.projetorestful.domain.Task;
import br.com.restful.projetorestful.domain.User;
import br.com.restful.projetorestful.repository.TaskRepository;
import br.com.restful.projetorestful.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/Task")
@AllArgsConstructor
public class TaskRest {

    private TaskService taskService;

    @GetMapping("/UserTasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Task>> findAll(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(taskService.findAllByUserId(id));
    }



    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(taskService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Validated
    public ResponseEntity<Task> save(@Valid @RequestBody Task task){
        return ResponseEntity.ok(taskService.create(task));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validated
    public ResponseEntity<Task> update(@Valid @RequestBody Task taskUpdate, @Valid @PathVariable("id") Long id){
        return ResponseEntity.ok().body(taskService.update(taskUpdate));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
