package br.com.restful.projetorestful.repository;

import br.com.restful.projetorestful.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
