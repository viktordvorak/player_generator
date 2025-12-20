package cz.dvorakv.dto

import cz.dvorakv.dao.entity.PlayerDetail

/**
 * @author dvorka
 * @since 25.10.2025
 */
data class GenerateRequest(
    val params: GeneratorParams,
    val players: List<PlayerDetail>
)
