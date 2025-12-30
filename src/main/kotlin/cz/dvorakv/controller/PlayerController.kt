package cz.dvorakv.controller

import cz.dvorakv.dao.filter.PlayerFilter
import cz.dvorakv.service.PlayerService
import cz.dvorakv.dto.PlayerDto
import jakarta.validation.Valid

import org.springframework.web.bind.annotation.RequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author dvorka
 * @since 17.10.2025
 */
@RestController
@RequestMapping("/api")
class PlayerController {

    @Autowired
    lateinit var playerService: PlayerService

    @PostMapping("/player")
    fun addPerson(@Valid @RequestBody dto: PlayerDto): PlayerDto {
        return playerService.addPlayer(dto)
    }

    @PutMapping("/player/{id}")
    fun editPerson(@Valid @RequestBody dto: PlayerDto, id: Long): PlayerDto {
        return playerService.editPlayer(dto, id)
    }

    @DeleteMapping("/player/{id}")
    fun removePerson(@PathVariable id: Long): PlayerDto {
       return playerService.removePlayer(id)
    }

    @GetMapping("/player/{id}")
    fun getPlayer(@PathVariable id: Long): PlayerDto {
        return playerService.getPlayer(id)
    }

    @GetMapping("/player")
    fun getAllPlayers(): List<PlayerDto> {
        return playerService.getAllPlayers()
    }

    @GetMapping("/player/search")
    fun searchPlayers(@ModelAttribute filter: PlayerFilter): List<PlayerDto> {
        return playerService.getSearchPlayers(filter)
    }

}
