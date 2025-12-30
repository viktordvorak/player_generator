package cz.dvorakv.service

import cz.dvorakv.dao.entity.Match
import cz.dvorakv.dto.MatchDto

interface MatchService {

    fun saveGeneratedMatch(result: MatchDto): MatchDto

}