package cz.dvorakv.dao.filter

import cz.dvorakv.constants.PlayerType
import lombok.Data

@Data
class PlayerFilter {

    var name: String? = null
    var footballPlayer: Boolean? = null
    var playerType: PlayerType? = null

}
