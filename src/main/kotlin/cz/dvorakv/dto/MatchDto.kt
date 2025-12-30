package cz.dvorakv.dto

import com.fasterxml.jackson.annotation.JsonInclude
import cz.dvorakv.dao.entity.Team

@JsonInclude(JsonInclude.Include.NON_NULL)
class MatchDto {

    var homeTeam: TeamDto? = null
    var awayTeam: TeamDto? = null
    var homePlayers: List<PlayerDto>? = null
    var awayPlayers: List<PlayerDto>? = null
    var homeScore: Int? = null
    var awayScore: Int? = null

}
