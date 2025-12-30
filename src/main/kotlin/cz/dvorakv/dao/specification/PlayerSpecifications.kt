package cz.dvorakv.dao.specification

import cz.dvorakv.constants.PlayerType
import cz.dvorakv.dao.entity.PlayerDetail
import org.springframework.data.jpa.domain.Specification

object PlayerSpecifications {

    fun hasName(name: String?) =
        Specification<PlayerDetail> { root, _, cb ->
            name?.takeIf { it.isNotBlank() }?.let {
                cb.like(
                    cb.lower(root.get("name")),
                    "%${it.lowercase()}%"
                )
            }
        }

    fun hasPlayerType(type: PlayerType?) =
        Specification<PlayerDetail> { root, _, cb ->
            type?.let {
                cb.equal(root.get<PlayerType>("playerType"), it)
            }
        }

    fun isFootballPlayer(footballPlayer: Boolean?) =
        Specification<PlayerDetail> { root, _, cb ->
            footballPlayer?.let {
                cb.equal(root.get<Boolean>("footballPlayer"), it)
            }
        }

}

