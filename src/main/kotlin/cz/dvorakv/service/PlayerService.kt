package cz.dvorakv.service

import cz.dvorakv.dto.PlayerDto

/**
 * @author dvorka
 * @since 17.10.2025
 */
interface PlayerService {

    fun addPlayer(dto: PlayerDto): PlayerDto

    fun editPlayer(dto: PlayerDto, id:Long): PlayerDto

    fun getPlayer(id: Long): PlayerDto

    fun getAllPlayers(): List<PlayerDto>

    fun removePlayer(id: Long): PlayerDto

}