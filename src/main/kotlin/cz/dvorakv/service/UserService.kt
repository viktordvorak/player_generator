package cz.dvorakv.service

import cz.dvorakv.dao.entity.User
import cz.dvorakv.dto.UserDto

interface UserService {

    fun register(username: String, password: String): UserDto

    fun validateUsername(username: String): Boolean

    fun getUser(username: String): UserDto?

    fun loadUser(username: String): User?
}
