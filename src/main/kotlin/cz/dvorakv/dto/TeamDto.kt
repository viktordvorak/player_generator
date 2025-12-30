package cz.dvorakv.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

class TeamDto {

    @JsonProperty("_id")
    @Schema(description = "Team ID")
    var id: Long = 0
    @Schema(description = "Team name")
    var name: String? = null

}