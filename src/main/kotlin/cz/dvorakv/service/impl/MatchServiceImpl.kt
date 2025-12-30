package cz.dvorakv.service.impl

import cz.dvorakv.constants.MatchSide
import cz.dvorakv.dao.MatchRepository
import cz.dvorakv.dao.TeamRepository
import cz.dvorakv.dao.entity.Match
import cz.dvorakv.dao.entity.MatchLineUp
import cz.dvorakv.dao.entity.MatchTeam
import cz.dvorakv.dto.MatchDto
import cz.dvorakv.mapper.MatchLineUpMapper
import cz.dvorakv.mapper.MatchMapper
import cz.dvorakv.service.MatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class MatchServiceImpl : MatchService {

    @Autowired
    lateinit var teamRepository: TeamRepository
    @Autowired
    lateinit var matchRepository: MatchRepository
    
//    @Autowired
//    lateinit var mapper: MatchLineUpMapper = MatchLineUpMapper.i
//    @Autowired
//    lateinit var matchMapper: MatchMapper

    override fun saveGeneratedMatch(result: MatchDto): MatchDto {
        val homeTeam = teamRepository.findById(result.homeTeam!!.id).orElseThrow {IllegalArgumentException("Team not found")}
        val awayTeam = teamRepository.findById(result.awayTeam!!.id).orElseThrow {IllegalArgumentException("Team not found")}

        val game = Match().apply {
            playedAt = LocalDate.now()
            homeScore = result.homeScore
            awayScore = result.awayScore
        }

        val homeMatchTeam = MatchTeam().apply {
            match = game
            team = homeTeam
            side = MatchSide.HOME
        }
        val awayMatchTeam = MatchTeam().apply {
            match = game
            team = awayTeam
            side = MatchSide.AWAY
        }

        homeMatchTeam.lineup.addAll(result.homePlayers!!
            .map { MatchLineUpMapper.toEntity(it, homeMatchTeam) })
        awayMatchTeam.lineup.addAll(result.awayPlayers!!
            .map { MatchLineUpMapper.toEntity(it, awayMatchTeam) })

        game.teams.add(homeMatchTeam)
        game.teams.add(awayMatchTeam)

        val savedMatch = matchRepository.save(game)

        return MatchMapper.toDto(savedMatch)
    }

}