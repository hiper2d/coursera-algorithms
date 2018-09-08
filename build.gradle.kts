plugins {
    java
    kotlin("jvm") version "1.2.61" apply false
}

val hamcrestVersion: String by project
val junitJupiterVersion: String by project

subprojects {
    repositories {
        jcenter()
    }

    apply {
        plugin("java")
        plugin("checkstyle")
        plugin("findbugs")
        plugin("pmd")
        plugin("org.jetbrains.kotlin.jvm")
    }

    configure<CheckstyleExtension> {
        configFile = file("../config/checkstyle/coursera-checkstyle.xml")
        configProperties = mapOf(
            "baseDir" to rootDir
        )
        toolVersion = "8.2"
        isIgnoreFailures = true
        // Dirty hack to remove checkstyle tasks from build cycle if they are not called explicitly
        // Read more here: https://discuss.gradle.org/t/how-to-not-run-checkstyle-findbugs-tasks-automatically/7383/7
        sourceSets = emptyList<SourceSet>()
    }

    configure<FindBugsExtension> {
        excludeFilterConfig = resources.text.fromFile("../config/findbugs/coursera-findbugs.xml")
        toolVersion = "3.0.1"
        isIgnoreFailures = true
        sourceSets = emptyList<SourceSet>()
    }

    configure<PmdExtension> {
        toolVersion = "5.7.0"
        isIgnoreFailures = true
        sourceSets = emptyList<SourceSet>()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    base {
        archivesBaseName = project.name.toLowerCase()
    }

    dependencies {
        implementation(files("../lib/algs4.jar"))
        testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jre8")
        testImplementation("org.hamcrest:java-hamcrest:$hamcrestVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    }

    tasks {
        create<Zip>("zip") {
            baseName = project.name.toLowerCase()
            from("src/main/java")
            include("*")
        }

        create("analyze") {
            dependsOn("checkstyleMain", "findbugsMain", "pmdMain")
        }

        withType<Test> {
            useJUnitPlatform()
        }

        withType<FindBugs> {
            reports {
                xml.isEnabled = false
                html.isEnabled = true
            }
            enabled = gradle.startParameter.taskNames.contains("analyze")
        }
    }
}