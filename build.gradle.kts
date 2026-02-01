plugins {
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("jvm") version "2.0.21"
	kotlin("plugin.spring") version "2.0.21"
}

group = "cz.dvorakv"
version = "1.0-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// --- Spring Boot starters (verze se řídí BOMem Spring Bootu 3.5.6) ---
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")

	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// --- Database ---
	runtimeOnly("com.mysql:mysql-connector-j:9.0.0") // novější driver pro MySQL 8/9

	// --- OpenAPI / Swagger ---
	//implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

	// --- Mapping & JPA Modelgen ---
	implementation("org.mapstruct:mapstruct:1.6.2")
	//kapt("org.mapstruct:mapstruct-processor:1.6.2")
	//kapt("org.hibernate.orm:hibernate-jpamodelgen:6.6.1.Final")

	// --- Lombok ---
	compileOnly("org.projectlombok:lombok:1.18.34")
	annotationProcessor("org.projectlombok:lombok:1.18.34")

	// --- Logging (Logback 1.5.x – opravené %clr a %wEx) ---
	//implementation("ch.qos.logback:logback-classic:1.5.12")
	//implementation("ch.qos.logback:logback-core:1.5.12")

	// --- Test ---
	//testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("org.mockito:mockito-core:5.14.1")
	//testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
}

// --- MapStruct konfigurace ---
//kapt {
//	arguments {
//		arg("mapstruct.suppressGeneratorTimestamp", "true")
//		arg("mapstruct.defaultComponentModel", "spring")
//	}
//}

// --- Testy ---
tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named<Test>("test") { enabled = false }

// --- Kotlin kompilátor ---
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		jvmTarget = "21"
		freeCompilerArgs = listOf("-Xjsr305=strict")
	}

}
