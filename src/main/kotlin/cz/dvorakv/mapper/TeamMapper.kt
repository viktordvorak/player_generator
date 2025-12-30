package cz.dvorakv.mapper

import cz.dvorakv.dao.entity.Team
import cz.dvorakv.dto.TeamDto
import org.springframework.stereotype.Component

@Component
object TeamMapper {

    fun toDto(entity: Team): TeamDto {
        return TeamDto().apply {
            name = entity.name
        }
    }

}