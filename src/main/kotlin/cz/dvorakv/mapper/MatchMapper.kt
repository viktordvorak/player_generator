package cz.dvorakv.mapper

import cz.dvorakv.constants.MatchSide
import cz.dvorakv.dao.entity.Match
import cz.dvorakv.dto.MatchDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
object MatchMapper {

//    @Autowired
//    lateinit var teamMapper: TeamMapper
//    @Autowired
//    lateinit var lineupMapper: MatchLineUpMapper

    fun toDto(entity: Match): MatchDto {
        val homeMatchTeam = entity.teams.filter { it.side == MatchSide.HOME }.first()
        val awayMatchTeam = entity.teams.filter { it.side == MatchSide.AWAY }.first()

        return MatchDto().apply {
            homeTeam = TeamMapper.toDto(homeMatchTeam.team!!)
            awayTeam = TeamMapper.toDto(awayMatchTeam.team!!)
            homePlayers = homeMatchTeam.lineup.map { MatchLineUpMapper.toDto(it) }
            awayPlayers = awayMatchTeam.lineup.map { MatchLineUpMapper.toDto(it) }
            homeScore = entity.homeScore
            awayScore = entity.awayScore
        }
    }

}