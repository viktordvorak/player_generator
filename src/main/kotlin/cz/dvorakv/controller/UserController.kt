package cz.dvorakv.controller

import cz.dvorakv.dto.AuthRequest
import cz.dvorakv.dto.UserDto
import cz.dvorakv.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController {

    @Autowired
    lateinit var service: UserService

    @PostMapping("/user")
    fun addUser(@RequestBody body: AuthRequest): UserDto {
       return service.register(body.username, body.password)
    }
    
}