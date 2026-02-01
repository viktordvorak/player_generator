package cz.dvorakv.mapper

import cz.dvorakv.dao.entity.Team
import cz.dvorakv.dto.TeamDto
import org.springframework.stereotype.Component

@Component
object TeamMapper {

    fun toDto(source: Team): TeamDto {
        return TeamDto().apply {
            name = source.name
        }
    }

    fun toEntity(source: TeamDto): Team {
        return Team().apply {
            id = source.id
            name = source.name
        }
    }

}