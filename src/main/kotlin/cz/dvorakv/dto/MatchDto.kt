package cz.dvorakv.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
class MatchDto {

    @JsonProperty("_id")
    @Schema(description = "Match ID")
    var id: Long = 0
    var homeTeam: TeamDto? = null
    var awayTeam: TeamDto? = null
    var playedAt: LocalDate? = null
    var homePlayers: List<PlayerDto>? = null
    var awayPlayers: List<PlayerDto>? = null
    var homeScore: Int? = null
    var awayScore: Int? = null

}
