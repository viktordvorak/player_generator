package cz.dvorakv.mapper

import cz.dvorakv.constants.MatchSide
import cz.dvorakv.dao.entity.Match
import cz.dvorakv.dto.MatchDto
import org.springframework.stereotype.Component

@Component
object MatchMapper {

    fun toDto(source: Match): MatchDto {
        val homeMatchTeam = source.teams.filter { it.side == MatchSide.HOME }.first()
        val awayMatchTeam = source.teams.filter { it.side == MatchSide.AWAY }.first()

        return MatchDto().apply {
            id = source.id!!
            playedAt = source.playedAt
            homeTeam = TeamMapper.toDto(homeMatchTeam.team!!)
            awayTeam = TeamMapper.toDto(awayMatchTeam.team!!)
            homePlayers = homeMatchTeam.lineup.map { MatchLineUpMapper.toDto(it) }
            awayPlayers = awayMatchTeam.lineup.map { MatchLineUpMapper.toDto(it) }
            homeScore = source.homeScore
            awayScore = source.awayScore
        }
    }

    fun toEntity(source: MatchDto): Match {
        return Match().apply {
            id = source.id
            playedAt = source.playedAt
            homeScore = source.homeScore
            awayScore = source.awayScore
        }
    }

}
