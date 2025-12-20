package cz.dvorakv.dao

import cz.dvorakv.dao.entity.PlayerDetail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author dvorka
 * @since 17.10.2025
 */
@Repository
interface PlayerRepository : JpaRepository<PlayerDetail, Long> {

}