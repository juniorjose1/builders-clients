package br.com.builders.apiclients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.builders.apiclients.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByLogin(String login);
	
}
