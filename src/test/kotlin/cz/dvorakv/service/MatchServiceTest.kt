package cz.dvorakv.service

import cz.dvorakv.dto.MatchDto
import cz.dvorakv.dto.PlayerDto
import cz.dvorakv.dto.TeamDto
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import kotlin.random.Random

@SpringBootTest
//@Transactional
class MatchServiceTest {

    @Autowired
    private lateinit var matchService: MatchService
    @Autowired
    private lateinit var playerService: PlayerService

    private lateinit var playersHome: List<PlayerDto>
    private lateinit var playersAway: List<PlayerDto>

    @BeforeEach
    fun setUp() {

        val players = playerService.getAllPlayers()
        val mid = players.size / 2
        playersHome = players.take(mid)
        playersAway = players.drop(mid)
        assertTrue(players.isNotEmpty(), "⚠️ Test vyžaduje, aby v databázi byli hráči.")
    }

    @Test
    fun createMatch() {
        val request = MatchDto().apply {
            homeTeam = TeamDto().apply { id = 1; name = "home" }
            awayTeam = TeamDto().apply { id = 2; name = "away" }
            homePlayers = playersHome
            awayPlayers = playersAway
            homeScore = Random.nextInt(0, 10)
            awayScore =  Random.nextInt(0, 10)
        }

        val response = matchService.saveGeneratedMatch(request)
        assertTrue(response.awayPlayers!!.size.equals(playersAway.size),"⚠️ Saved players are wrong")
    }

}