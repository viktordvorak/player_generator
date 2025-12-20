package cz.dvorakv.dao

import cz.dvorakv.dao.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author dvorka
 * @since 17.10.2025
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?

}