package cz.dvorakv.mapper

import cz.dvorakv.constants.PlayerType
import cz.dvorakv.dao.entity.MatchLineUp
import cz.dvorakv.dao.entity.MatchTeam
import cz.dvorakv.dto.PlayerDto
import org.springframework.stereotype.Component

@Component
object MatchLineUpMapper {

    fun toEntity(playerDto: PlayerDto, teamMatch: MatchTeam): MatchLineUp {
        return MatchLineUp().apply {
            matchTeam = teamMatch
            player = PlayerDetailMapper.mapToEntity(playerDto)
            position = playerDto.playerType.name
            rating = playerDto.getOverallSummary()
        }
    }

    fun toDto(entity: MatchLineUp): PlayerDto {
        return PlayerDto().apply {
            id = entity.player?.id!!
            name = entity.player?.name!!
            offense = entity.player?.offense!!
            defense = entity.player?.defense!!
            creativity = entity.player?.creativity!!
            runningAbility = entity.player?.runningAbility!!
            playerType = PlayerType.valueOf(entity.player?.playerType!!.name)
            footballPlayer = entity.player?.footballPlayer!!
        }
    }

}