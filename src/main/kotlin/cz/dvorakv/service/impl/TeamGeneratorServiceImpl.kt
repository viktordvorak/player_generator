package cz.dvorakv.service.impl

import cz.dvorakv.constants.Category
import cz.dvorakv.constants.PlayerType
import cz.dvorakv.dto.GeneratedResults
import cz.dvorakv.dto.GeneratorParams
import cz.dvorakv.dto.PlayerDto
import cz.dvorakv.service.TeamGeneratorService
import org.springframework.stereotype.Service
import kotlin.math.abs
import kotlin.random.Random

@Service
class TeamGeneratorServiceImpl : TeamGeneratorService {

    override fun generateTeams(params: GeneratorParams): GeneratedResults {
        val players = params.players ?: error("Players are not set for generating")
        val maxIterations = 1000
        val random = Random(System.nanoTime())

        repeat(maxIterations) { iteration ->
            val shuffled = players.shuffled(random)
            val mid = shuffled.size / 2
            val teamA = shuffled.take(mid)
            val teamB = shuffled.drop(mid)

            if (checkGenerationCondition(teamA, teamB, params)) {
                println("✅ Teams generated successfully after $iteration iterations")

                // vypočítání metrik
                val overallA = teamA.sumOf { it.defense!! + it.creativity!! + it.offense!! + it.runningAbility!! }
                val overallB = teamB.sumOf { it.defense!! + it.creativity!! + it.offense!! + it.runningAbility!! }

                val categoryA = teamCategorySummary(teamA)
                val categoryB = teamCategorySummary(teamB)

                val playerTypeA = teamA.groupingBy { it.playerType!! }.eachCount()
                val playerTypeB = teamB.groupingBy { it.playerType!! }.eachCount()

                val footballCountA = teamA.count { it.footballPlayer == true }
                val footballCountB = teamB.count { it.footballPlayer == true }

                val result = GeneratedResults(
                    teamA = teamA,
                    teamB = teamB,
                    overallSummaryTeamA = overallA,
                    overallSummaryTeamB = overallB,
                    categorySummaryTeamA = categoryA,
                    categorySummaryTeamB = categoryB,
                    playerTypeSummaryTeamA = playerTypeA,
                    playerTypeSummaryTeamB = playerTypeB,
                    footballPlayerCountTeamA = footballCountA,
                    footballPlayerCountTeamB = footballCountB
                )

                result.getInfo()
                return  result;
            }
        }

        println("⚠️ No suitable team combination found after $maxIterations iterations")

        // fallback – pokud žádný výsledek nevyhovuje
        return GeneratedResults(
            teamA = emptyList(),
            teamB = emptyList(),
            overallSummaryTeamA = 0,
            overallSummaryTeamB = 0,
            categorySummaryTeamA = emptyMap(),
            categorySummaryTeamB = emptyMap(),
            playerTypeSummaryTeamA = emptyMap(),
            playerTypeSummaryTeamB = emptyMap(),
            footballPlayerCountTeamA = 0,
            footballPlayerCountTeamB = 0
        )
    }

    fun teamOverallSummary(players: List<PlayerDto>): Int {
        return players.sumOf { it.getOverallSummary() }
    }

    private fun teamCategorySummary(players: List<PlayerDto>): Map<Category, Int> {
        return mapOf(
            Pair(Category.DEFENSE, players.sumOf {it.defense}),
            Pair(Category.OFFENSE, players.sumOf { it.offense }),
            Pair(Category.CREATIVITY, players.sumOf { it.creativity }),
            Pair(Category.RUNNING_ABILITY, players.sumOf { it.runningAbility })
        )
    }

    private fun checkOverallSummary(teamA: List<PlayerDto>, teamB: List<PlayerDto>): Boolean {
        val overallDiff = abs(teamOverallSummary(teamA) - teamOverallSummary(teamB))
        return if (abs(overallDiff) > OVERALL_DVIATION) return false else true
    }

    private fun checkCategorySummary(teamA: List<PlayerDto>, teamB: List<PlayerDto>): Boolean {
        val categorySummaryTeamA = teamCategorySummary(teamA)
        val categorySummaryTeamB = teamCategorySummary(teamB)

        return categorySummaryTeamA.keys.all { key ->
            val diff = abs((categorySummaryTeamA[key] ?: 0) - (categorySummaryTeamB[key] ?: 0))
            diff < CATEGORY_DEVIATION
        }
    }

    private fun checkPlayerTypeSummary(teamA: List<PlayerDto>, teamB: List<PlayerDto>): Boolean {
        val playerTypeTeamA = teamPlayerTypeSummary(teamA)
        val playerTypeTeamB = teamPlayerTypeSummary(teamB)

        return (playerTypeTeamA.keys + playerTypeTeamB.keys).all { key ->
            val diff = abs((playerTypeTeamA[key] ?: 0) - (playerTypeTeamB[key] ?: 0))
            diff < PLAYER_TYPE_DEVIATION
        }
    }

    private fun checkFootballPlayerSummary(teamA: List<PlayerDto>, teamB: List<PlayerDto>): Boolean {
        val playerTypeSummaryTeamA = teamA.filter { it.footballPlayer == true }.count()
        val playerTypeSummaryTeamB = teamB.filter { it.footballPlayer == true }.count()

        return abs(playerTypeSummaryTeamA - playerTypeSummaryTeamB) > FOOTABLL_PLAYER_DEVIATION
    }

    private fun teamPlayerTypeSummary(players: List<PlayerDto>): Map<PlayerType, Int> =
        players.groupingBy { it.playerType ?: PlayerType.DEFAULT }.eachCount()



    private fun checkGenerationCondition(teamA: List<PlayerDto>, teamB: List<PlayerDto>, params: GeneratorParams): Boolean {
        val overallOk = !params.overallSummary || checkOverallSummary(teamA, teamB)
        val categoryOk = !params.categorySummary || checkCategorySummary(teamA, teamB)
        val playerTypeOk = !params.playerTypeSummary || checkPlayerTypeSummary(teamA, teamB)
        val footballOk = !params.footballPlayerSummary || checkFootballPlayerSummary(teamA, teamB)

        return overallOk && categoryOk && playerTypeOk && footballOk
    }

    companion object {

        const val OVERALL_DVIATION: Int = 5
        const val CATEGORY_DEVIATION: Int = 5
        const val PLAYER_TYPE_DEVIATION: Int = 2
        const val FOOTABLL_PLAYER_DEVIATION: Int = 1

    }

}
