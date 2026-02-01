package cz.dvorakv.mapper

import cz.dvorakv.constants.MatchSide
import cz.dvorakv.dao.entity.Match
import cz.dvorakv.dao.entity.MatchTeam
import cz.dvorakv.dto.TeamDto
import org.springframework.stereotype.Component

@Component
object MatchTeamMapper {

    fun toEntity(match: Match, teamDto: TeamDto, side: MatchSide): MatchTeam {
        return MatchTeam().apply {
            this.match = match
            this.team = TeamMapper.toEntity(teamDto)
            this.side = side
        }
    }

}