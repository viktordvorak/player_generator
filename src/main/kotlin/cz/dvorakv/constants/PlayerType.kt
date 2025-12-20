package cz.dvorakv.constants

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue

enum class PlayerType {
    @JsonEnumDefaultValue
    DEFAULT,
    CREATIVE,
    DEFENSIVE,
    OFFENSIVE

}