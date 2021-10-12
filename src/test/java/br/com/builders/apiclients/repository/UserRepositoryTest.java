package br.com.builders.apiclients.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.builders.apiclients.model.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	@DisplayName("should register a user and search for it by searching for its login")
	public void testSuccessFindUser() {
		User user = new User();
		user.setLogin("vitor");
		user.setPassword("123456");
		userRepository.save(user);
		User userFounded = userRepository.findByLogin("vitor");
		Assertions.assertThat(userFounded).isNotNull();
		Assertions.assertThat(userFounded).isEqualTo(user);
	}
	
	@Test
	@DisplayName("should not find a user that is not registered")
	public void testFailFindUser() {
		String login = "rodolfo";
		User userFounded = userRepository.findByLogin(login);
		Assertions.assertThat(userFounded).isNull();
	}

}
