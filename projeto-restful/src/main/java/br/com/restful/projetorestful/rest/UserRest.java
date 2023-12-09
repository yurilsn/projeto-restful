package br.com.restful.projetorestful.rest;

import br.com.restful.projetorestful.domain.User;
import br.com.restful.projetorestful.repository.TaskRepository;
import br.com.restful.projetorestful.repository.UserRepository;
import br.com.restful.projetorestful.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Optional<User>> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(userRepository.findById(id));
    }

//    @PostMapping("/SalvarUsuario")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<User> save(@RequestBody User user){
//        taskRepository.saveAll(user.getTasks());
//        return ResponseEntity.ok(userRepository.save(user));
//    }
//    @PostMapping("/SalvarUsuarios")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<List<User>> save(@RequestBody User user){
//        return ResponseEntity.ok(userRepository.save(user));
//    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> update(@RequestBody User userUpdate, @PathVariable("id") Long id){
        var user = userRepository.findById(id).get();
        BeanUtils.copyProperties(userUpdate, user);
        return ResponseEntity.ok().body(userRepository.save(user));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
