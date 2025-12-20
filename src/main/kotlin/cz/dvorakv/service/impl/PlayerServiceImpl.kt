package cz.dvorakv.service.impl

import cz.dvorakv.dto.PlayerDto
import cz.dvorakv.dao.PlayerRepository
import cz.dvorakv.mapper.PlayerDetailMapper
import cz.dvorakv.service.PlayerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author dvorka
 * @since 17.10.2025
 */
@Service
class PlayerServiceImpl : PlayerService {

    @Autowired
    lateinit var playerRepository: PlayerRepository

    @Autowired
    lateinit var mapper: PlayerDetailMapper

    override fun addPlayer(dto: PlayerDto): PlayerDto {
       val entity = mapper.mapToEntity(dto);
       val savedEntity = playerRepository.save(entity)
       return mapper.mapToDto(savedEntity)
    }

    override fun editPlayer(dto: PlayerDto, id: Long): PlayerDto {
        playerRepository.existsById(id) ?: error("Player not found")
        val entity = mapper.mapToEntity(dto).apply { this.id = id }
        val savedEntity = playerRepository.save(entity)
        return  mapper.mapToDto(savedEntity)
    }

    override fun getPlayer(id: Long): PlayerDto {
        val entity = playerRepository.getById(id) ?: error("Player not found")
        return mapper.mapToDto(entity)
    }

    override fun getAllPlayers(): List<PlayerDto> {
        val entities = playerRepository.findAll() ?: error("Players not found")
        return entities.map { mapper.mapToDto(it) }
    }

    override fun removePlayer(id: Long): PlayerDto {
        val entity = playerRepository.getById(id) ?: error("Player not found")
        playerRepository.deleteById(id)
        return mapper.mapToDto(entity)
    }

}