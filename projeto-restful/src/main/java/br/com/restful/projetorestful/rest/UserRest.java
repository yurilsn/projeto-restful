package br.com.restful.projetorestful.rest;

import br.com.restful.projetorestful.domain.User;
import br.com.restful.projetorestful.repository.TaskRepository;
import br.com.restful.projetorestful.repository.UserRepository;
import br.com.restful.projetorestful.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/User")
@AllArgsConstructor
public class UserRest {

    private final UserRepository userRepository;
    private final UserService userService;
    private final TaskRepository taskRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Validated(User.CreateUser.class)
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        return ResponseEntity.ok(userService.create(user));
//        this.userService.create(user);
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
//        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Validated(User.UpdateUser.class)
    public ResponseEntity<User> update(@Valid @RequestBody User userUpdate, @Valid @PathVariable("id") Long id){
        return ResponseEntity.ok().body(userService.update(userUpdate));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
