package cz.dvorakv.service.impl

import cz.dvorakv.constants.MatchSide
import cz.dvorakv.dao.MatchRepository
import cz.dvorakv.dao.TeamRepository
import cz.dvorakv.dto.MatchDto
import cz.dvorakv.mapper.MatchLineUpMapper
import cz.dvorakv.mapper.MatchMapper
import cz.dvorakv.mapper.MatchTeamMapper
import cz.dvorakv.service.MatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MatchServiceImpl : MatchService {

    @Autowired
    lateinit var teamRepository: TeamRepository

    @Autowired
    lateinit var matchRepository: MatchRepository

    @Transactional
    override fun saveGeneratedMatch(dto: MatchDto): MatchDto {

        val match = MatchMapper.toEntity(dto)

        val homeTeam = MatchTeamMapper.toEntity(
            match,
            dto.homeTeam!!,
            MatchSide.HOME
        )

        val awayTeam = MatchTeamMapper.toEntity(
            match,
            dto.awayTeam!!,
            MatchSide.AWAY
        )

        match.teams.addAll(listOf(homeTeam, awayTeam))

        homeTeam.lineup = dto.homePlayers!!
            .map { MatchLineUpMapper.toEntity(it, homeTeam) }
            .toMutableList()

        awayTeam.lineup = dto.awayPlayers!!
            .map { MatchLineUpMapper.toEntity(it, awayTeam) }
            .toMutableList()

        val saved = matchRepository.save(match)
        return MatchMapper.toDto(saved)
    }

    override fun edit(match: MatchDto, id: Long): MatchDto {
        if (!matchRepository.findById(id).isPresent) {
            throw IllegalArgumentException("Data not found")
        }
        val entity = MatchMapper.toEntity(match)

        val homeTeam = MatchTeamMapper.toEntity(entity, match.homeTeam!!, MatchSide.HOME)
        val awayTeam = MatchTeamMapper.toEntity(entity, match.awayTeam!!, MatchSide.AWAY)

        val homePlayers = match.homePlayers!!.map { MatchLineUpMapper.toEntity(it, homeTeam) }
        val awayPlayers = match.awayPlayers!!.map { MatchLineUpMapper.toEntity(it, awayTeam) }

        homeTeam.lineup = homePlayers.toMutableList()
        awayTeam.lineup = awayPlayers.toMutableList()
        entity.teams = mutableListOf(homeTeam, awayTeam)

        val savedEntity = matchRepository.save(entity)
        return MatchMapper.toDto(savedEntity)
    }

    override fun delete(id: Long): MatchDto {
        val entity = matchRepository.getById(id) ?: error("Delete fail - Match does not exist")
        matchRepository.deleteById(id)
        return MatchMapper.toDto(entity)
    }

    override fun getAll(): List<MatchDto> {
        val entities = matchRepository.findAll();
        val result = entities.map {
            MatchMapper.toDto(it)
        }
        return result
    }

}