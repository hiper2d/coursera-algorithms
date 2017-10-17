buildscript {
    repositories { mavenCentral() }
    dependencies { classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.1") }
}

plugins {
    java
    id("org.jetbrains.kotlin.jvm") version "1.1.51"
}

val hamcrestVersion by project
val junitJupiterVersion by project

subprojects {
    apply {
        plugin("java")
        plugin("checkstyle")
        plugin("findbugs")
        plugin("pmd")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.junit.platform.gradle.plugin")
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
        testCompileOnly("org.jetbrains.kotlin:kotlin-stdlib-jre8")
        testCompileOnly("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        testImplementation("org.hamcrest:java-hamcrest:$hamcrestVersion")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    }

    tasks {
        "zip"(type = Zip::class) {
            baseName = project.name.toLowerCase()
            dependsOn("build") // todo: figure out why it's not working
            from("src/main/java")
            include("*")
        }

        "analyze" {
            dependsOn("checkstyleMain", "findbugsMain", "pmdMain")
        }

        withType<FindBugs> {
            reports {
                xml.isEnabled = false
                html.isEnabled = true
            }
            enabled = gradle.startParameter.taskNames.contains("analyze")
        }
    }

    repositories {
        mavenCentral()
    }
}