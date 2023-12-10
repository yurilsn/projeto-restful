package br.com.restful.projetorestful.repository;

import br.com.restful.projetorestful.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id);


//    @Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
//    List<Task> findByUser_id(@Param("id") Long id);

//    @Query(value = "SELECT * FROM Task t WHERE t.user_id = :id", nativeQuery = true)
//    List<Task> findByUser_id(@Param("id") Long id);



}
