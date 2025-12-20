package cz.dvorakv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackages = ["cz.dvorakv.dao"])
@EntityScan(basePackages = ["cz.dvorakv.dao"])
class PlayerRandomGeneratorApplication

fun main(args: Array<String>) {
	runApplication<PlayerRandomGeneratorApplication>(*args)
}
