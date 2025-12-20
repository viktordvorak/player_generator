package cz.dvorakv.service

import cz.dvorakv.dao.PlayerRepository
import cz.dvorakv.dao.UserRepository
import cz.dvorakv.dao.entity.User
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) {

    @Test
    fun addUser() {
        val username = "viktor.dvorak";
        val password =  "heslo"

        val result = userService.register(username, password)
        assertTrue(result.username.equals(username), "username is not as expected")
    }

}