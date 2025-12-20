package cz.dvorakv.dao.entity

import cz.dvorakv.constants.PlayerType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

/**
 * @author dvorka
 * @since 17.10.2025
 */
@Entity(name = "PlayerDetail")
class PlayerDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    @Min(1) @Max(10)
    var defense: Int = 1

    @Column(nullable = false)
    @Min(1) @Max(10)
    var creativity: Int = 1

    @Column(nullable = false)
    @Min(1) @Max(10)
    var offense: Int = 1

    @Column(nullable = false)
    @Min(1) @Max(10)
    var runningAbility: Int = 1

    @Column(nullable = false)
    var playerType: PlayerType = PlayerType.DEFAULT

    @Column
    var footballPlayer: Boolean = false

}