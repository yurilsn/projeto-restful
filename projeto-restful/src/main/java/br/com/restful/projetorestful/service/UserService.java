package br.com.restful.projetorestful.service;


import br.com.restful.projetorestful.domain.User;
import br.com.restful.projetorestful.repository.TaskRepository;
import br.com.restful.projetorestful.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;


    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! id:" + id + ", Tipo:" + User.class.getName()
        ));
//        return user;
    }

    @Transactional
    public User create(User user){
        user.setId(null);
        user = this.userRepository.save(user);
        this.taskRepository.saveAll(user.getTasks());
        return user;
    }

    @Transactional
    private User update(User user){
        User updateUser = findById(user.getId());
        updateUser.setPassword(user.getPassword());
        return userRepository.save(updateUser);
    }

    private void delete(Long id){
        findById(id);
        try{
            this.userRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

}
