package cz.dvorakv.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class JacksonCheck(val mapper: ObjectMapper) : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("✅ Jackson modules loaded: ${mapper.registeredModuleIds}")
    }
}