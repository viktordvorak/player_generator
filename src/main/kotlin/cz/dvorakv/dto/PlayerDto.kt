package cz.dvorakv.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import cz.dvorakv.constants.PlayerType
import cz.dvorakv.dao.entity.PlayerDetail
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

/**
 * @author dvorka
 * @since 17.10.2025
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
class PlayerDto {
    @JsonProperty("_id")
    @Schema(description = "Player ID")
    var id: Long = 0

    @Schema(description = "Player name")
    var name: String = ""

    @Schema(description = "Player defence value")
    @field:Min(value = 1, message = "Defense must be at least 1")
    @field:Max(value = 10, message = "Defense must be at most 10")
    var defense: Int = 0

    @Schema(description = "Player creativity value")
    @field:Min(value = 1, message = "Creativity must be at least 1")
    @field:Max(value = 10, message = "Creativity must be at most 10")
    var creativity: Int = 0

    @Schema(description = "Player offense value")
    @field:Min(value = 1, message = "Offense must be at least 1")
    @field:Max(value = 10, message = "Offense must be at most 10")
    var offense: Int = 0

    @Schema(description = "Player running ability value")
    @field:Min(value = 1, message = "Running must be at least 1")
    @field:Max(value = 10, message = "Running must be at most 10")
    var runningAbility: Int = 0

    @Schema(description = "Player type")
    var playerType: PlayerType = PlayerType.CREATIVE

    @Schema(description = "Player is footballer")
    var footballPlayer: Boolean = false

    @Schema(description = "Player summary")
    fun getOverallSummary(): Int {
        return defense + offense + creativity + runningAbility + if (footballPlayer) 5 else 0
    }

}
