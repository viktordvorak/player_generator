package cz.dvorakv.mapper

import cz.dvorakv.constants.PlayerType
import cz.dvorakv.dao.entity.MatchLineUp
import cz.dvorakv.dao.entity.MatchTeam
import cz.dvorakv.dao.entity.PlayerDetail
import cz.dvorakv.dto.PlayerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
object MatchLineUpMapper {

//    @Autowired
//    lateinit var mapper: PlayerDetailMapper

    fun toEntity(dto: PlayerDto, teamMatch: MatchTeam): MatchLineUp {
        return MatchLineUp().apply {
            id = null
            matchTeam = teamMatch
            player = PlayerDetailMapper.mapToEntity(dto)
            position = dto.playerType.name
            rating = dto.getOverallSummary()
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