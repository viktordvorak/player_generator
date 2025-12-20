package cz.dvorakv.dto

import cz.dvorakv.constants.Category
import cz.dvorakv.constants.PlayerType

/**
 * @author dvorka
 * @since 25.10.2025
 */
data class GeneratedResults (
    val teamA: List<PlayerDto>,
    val teamB: List<PlayerDto>,
    val overallSummaryTeamA: Int,
    val overallSummaryTeamB: Int,
    val categorySummaryTeamA: Map<Category, Int>,
    val categorySummaryTeamB: Map<Category, Int>,
    val playerTypeSummaryTeamA: Map<PlayerType, Int>,
    val playerTypeSummaryTeamB: Map<PlayerType, Int>,
    val footballPlayerCountTeamA: Int,
    val footballPlayerCountTeamB: Int,
) {

    fun getInfo() {

            println("========= 🏆 Výsledky generace týmů 🏆 =========\n")

            println("⚽ Tým A:")
            println(" - Počet hráčů: ${teamA.size}")
            println(" - Celkový součet schopností: $overallSummaryTeamA")
            println(" - Počet fotbalistů: $footballPlayerCountTeamA")
            println(" - Hráči:")
            teamA.forEach {
                println(it.name)
            }
            println(" - Kategorie:")
            categorySummaryTeamA.forEach { (category, value) ->
                println("    • ${category.name}: $value")
            }
            println(" - Typy hráčů:")
            playerTypeSummaryTeamA.forEach { (type, value) ->
                println("    • ${type.name}: $value")
            }

            println("\n---------------------------------------------\n")

            println("⚽ Tým B:")
            println(" - Počet hráčů: ${teamB.size}")
            println(" - Celkový součet schopností: $overallSummaryTeamB")
            println(" - Počet fotbalistů: $footballPlayerCountTeamB")
            teamB.forEach {
                println(it.name)
            }
            println(" - Kategorie:")
            categorySummaryTeamB.forEach { (category, value) ->
                println("    • ${category.name}: $value")
            }
            println(" - Typy hráčů:")
            playerTypeSummaryTeamB.forEach { (type, value) ->
                println("    • ${type.name}: $value")
            }

            println("\n========= 🔍 Shrnutí 🔍 =========")
            val diff = overallSummaryTeamA - overallSummaryTeamB
            println("Rozdíl v souhrnném hodnocení: ${if (diff >= 0) "+$diff" else diff}")
            println("=========================================\n")
        }

}