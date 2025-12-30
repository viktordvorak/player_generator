package cz.dvorakv.dao.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import lombok.Data
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "matches")
class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(name = "played_at")
    var playedAt: LocalDate? = null
    var homeScore: Int? = null
    var awayScore: Int? = null
    @OneToMany(
        mappedBy = "match",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var teams: MutableList<MatchTeam> = mutableListOf()

}