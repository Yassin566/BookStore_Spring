package com.bookstore.dao.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.dao.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserRepositoryTest {
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

	@BeforeEach
	public void setUp() throws Exception {
        // set default data to use in all test methods
        User user = new User("yassin","yassin","ROLE_ADMIN",true);
        testEntityManager.merge(user);
    }
	@Test
	void testFindByUserName() {
		Optional<User> userEntity = userRepository.findByUserName("yassin");
        assertThat(userEntity.get().getUserName()).isEqualTo("yassin");
	}

}
