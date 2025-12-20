package cz.dvorakv.controller

import cz.dvorakv.dto.GeneratedResults
import cz.dvorakv.dto.GeneratorParams
import cz.dvorakv.service.TeamGeneratorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GeneratorController {

    @Autowired
    lateinit var service: TeamGeneratorService

    @PostMapping("/generator")
    fun generateTeam(@RequestBody params: GeneratorParams): GeneratedResults {
        return service.generateTeams(params)
    }

}
