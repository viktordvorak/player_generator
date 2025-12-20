package cz.dvorakv.service.impl

import cz.dvorakv.dao.UserRepository
import cz.dvorakv.dao.entity.User
import cz.dvorakv.dto.UserDto
import cz.dvorakv.exceptions.UsernameAlreadyExistsException
import cz.dvorakv.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val repo: UserRepository,
    private val encoder: PasswordEncoder
) : UserService {

    override fun register(username: String, password: String): UserDto {
        if (repo.findByUsername(username) != null) {
            throw UsernameAlreadyExistsException(username)
        }

        val hashed = encoder.encode(password)
        val savedEntity =  repo.save(User().apply {
            this.username = username
            this.password = hashed
        })
        return UserDto( id = savedEntity.id!!, username = savedEntity.username!! )
    }

    override fun validateUsername(username: String): Boolean {

        return repo.findByUsername(username) != null
    }

    override fun getUser(username: String): UserDto? {
        val savedEntity = repo.findByUsername(username)
        return UserDto(id = savedEntity?.id!!, username = savedEntity?.username!!)
    }

    override fun loadUser(username: String): User? = repo.findByUsername(username)
    
}
