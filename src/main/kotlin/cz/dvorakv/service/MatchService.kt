package cz.dvorakv.service

import cz.dvorakv.dto.MatchDto

interface MatchService {

    fun saveGeneratedMatch(match: MatchDto): MatchDto

    fun getAll(): List<MatchDto>

    fun edit(match: MatchDto, id: Long): MatchDto

    fun delete(id: Long): MatchDto

}