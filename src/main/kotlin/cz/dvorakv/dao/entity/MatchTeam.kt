package cz.dvorakv.dao.entity

import cz.dvorakv.constants.MatchSide
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class MatchTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    //Anotace se vždy čte z pohledu třídy,
    // kde je napsaná, MNOHO MatchTeam patří k JEDNOMU Match
    // Foreign key ALWAYS lives on @ManyToOne side
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    var match: Match? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    var team: Team? = null

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    var side: MatchSide? = null

    @OneToMany(
        mappedBy = "matchTeam",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var lineup: MutableList<MatchLineUp> = mutableListOf()

}
