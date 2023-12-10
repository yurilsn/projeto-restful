package br.com.restful.projetorestful.service;

import br.com.restful.projetorestful.domain.Task;
import br.com.restful.projetorestful.domain.User;
import br.com.restful.projetorestful.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;
    private UserService userService;


    public List<Task> findAllByUserId(Long userId){
        List<Task> tasks = userService.findById(userId).getTasks();
        return tasks;
    }

//    public List<Task> findAllByUserId(Long userId){
//        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
//        return tasks;
//    }

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrada! Id: " + id + ", Tipo" + Task.class.getName()
        ));
    }

    public Task create(Task task){
        User user = this.userService.findById(task.getUser().getId());
        task.setId(null);
        task.setUser(user);
        task = this.taskRepository.save(task);
        return task;
    }


    public Task update(Task task){
        Task updateTask = findById(task.getId());
        updateTask.setDescricao(task.getDescricao());
        return taskRepository.save(updateTask);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException(
                    "O id a ser deletado não existe no banco de dados."
            );
        }
    }
}
