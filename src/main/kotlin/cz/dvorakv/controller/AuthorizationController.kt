package cz.dvorakv.controller

import cz.dvorakv.dto.AuthRequest
import cz.dvorakv.dto.AuthResponse
import cz.dvorakv.service.UserService
import cz.dvorakv.utils.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthorizationController(
    private val service: UserService,
    private val jwt: JwtUtil,
    private val encoder: PasswordEncoder
) {

    @PostMapping("/auth/login")
    fun login(@RequestBody request: AuthRequest) : ResponseEntity<AuthResponse> {
        val user = service.loadUser(request.username) ?: return ResponseEntity.status(401).build()

        if (!encoder.matches(request.password, user.password)) {
            return ResponseEntity.status(401).build()
        }

        val token = jwt.generateToken(user.username!!)
        return ResponseEntity.ok(AuthResponse(token))
    }

    @PostMapping("/auth/registration")
    fun register(@RequestBody req: AuthRequest): ResponseEntity<Void> {
        service.register(req.username, req.password)
        return ResponseEntity.ok().build()
    }

}