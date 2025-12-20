package cz.dvorakv.service

import cz.dvorakv.dao.PlayerRepository
import cz.dvorakv.dto.GeneratorParams
import cz.dvorakv.dto.PlayerDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class TeamGeneratorServiceTest @Autowired constructor(
    private val playerRepository: PlayerRepository,
    private val teamGeneratorService: TeamGeneratorService
) {

    private lateinit var players: List<PlayerDto>

    @BeforeEach
    fun setUp() {
        // načtení všech hráčů z databáze a mapování na DTO
        players = playerRepository.findAll().map {
            PlayerDto().apply {
                id = it.id!!
                name = it.name!!
                defense = it.defense
                creativity = it.creativity
                offense = it.offense
                runningAbility = it.runningAbility
                playerType = it.playerType
                footballPlayer = it.footballPlayer
            }
        }
        assertTrue(players.isNotEmpty(), "⚠️ Test vyžaduje, aby v databázi byli hráči.")
    }

    @Test
    fun generateTeamsAllParams () {
        val params = GeneratorParams().apply {
            overallSummary = true
            categorySummary = true
            playerTypeSummary = true
            footballPlayerSummary = true
            players = players
        }

        val result = teamGeneratorService.generateTeams(params)

        // --- základní validace ---
        assertTrue(result.teamA.isNotEmpty(), "Team A nesmí být prázdný")
        assertTrue(result.teamB.isNotEmpty(), "Team B nesmí být prázdný")
        assertEquals(result.teamA.size, result.teamB.size, "Oba týmy musí mít stejný počet hráčů")

        // --- validace celkového skóre ---
        val diffOverall = kotlin.math.abs(result.overallSummaryTeamA - result.overallSummaryTeamB)
        println("Overall diff: $diffOverall (A=${result.overallSummaryTeamA}, B=${result.overallSummaryTeamB})")
        assertTrue(diffOverall <= 5, "Rozdíl celkového skóre je příliš velký")

        // --- validace kategorií ---
        result.categorySummaryTeamA.forEach { (category, valueA) ->
            val valueB = result.categorySummaryTeamB[category]
            assertNotNull(valueB)
            val diff = kotlin.math.abs(valueA - valueB!!)
            println("Category $category diff: $diff")
            assertTrue(diff <= 5, "Rozdíl v kategorii $category je příliš velký")
        }

        // --- validace playerType ---
        result.playerTypeSummaryTeamA.forEach { (type, countA) ->
            val countB = result.playerTypeSummaryTeamB[type] ?: 0
            val diff = kotlin.math.abs(countA - countB)
            println("PlayerType $type diff: $diff")
            assertTrue(diff <= 1, "Rozdíl počtu $type hráčů je příliš velký")
        }

        println("✅ Generátor týmů funguje správně.")
    }
}