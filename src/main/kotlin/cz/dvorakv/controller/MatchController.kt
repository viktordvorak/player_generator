package cz.dvorakv.controller

import cz.dvorakv.dto.MatchDto
import cz.dvorakv.service.MatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class MatchController {

    @Autowired
    lateinit var matchService: MatchService

    @GetMapping("/matches")
    fun get(): List<MatchDto> {
        return matchService.getAll()
    }

    @PostMapping("/matches")
    fun add(@RequestBody match: MatchDto): MatchDto {
        return matchService.saveGeneratedMatch(match)
    }

    @PutMapping("/matches/{id}")
    fun edit(@RequestBody match: MatchDto, @PathVariable id: Long): MatchDto {
        return matchService.edit(match, id)
    }

    @DeleteMapping("/matches/{id}")
    fun delete(@PathVariable id: Long): MatchDto {
        return matchService.delete(id)
    }

}
