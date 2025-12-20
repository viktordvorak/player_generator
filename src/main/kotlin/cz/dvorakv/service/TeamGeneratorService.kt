package cz.dvorakv.service

import cz.dvorakv.dto.GeneratedResults
import cz.dvorakv.dto.GeneratorParams

interface TeamGeneratorService {

    fun generateTeams(params: GeneratorParams): GeneratedResults

}