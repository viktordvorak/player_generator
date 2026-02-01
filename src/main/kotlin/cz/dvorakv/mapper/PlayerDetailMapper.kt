package cz.dvorakv.mapper

import cz.dvorakv.constants.PlayerType
import cz.dvorakv.dao.entity.PlayerDetail
import cz.dvorakv.dto.PlayerDto
import org.springframework.stereotype.Component

/**
 * @author dvorka
 * @since 17.10.2025
 */
@Component
object PlayerDetailMapper {

    fun mapToDto(source: PlayerDetail): PlayerDto {
        return PlayerDto().apply {
            id = source.id!!
            name = source.name!!
            defense = source.defense
            creativity = source.creativity
            offense = source.offense
            playerType = source.playerType
            runningAbility = source.runningAbility
            footballPlayer = source.footballPlayer
        }
    }

    fun mapToEntity(source: PlayerDto): PlayerDetail {
        return PlayerDetail().apply {
            id = source.id
            name = source.name
            defense = source.defense
            creativity = source.creativity
            offense = source.offense
            playerType = source.playerType ?: PlayerType.DEFAULT
            runningAbility = source.runningAbility ?: 0
            footballPlayer = source.footballPlayer ?: false
        }
    }

}