buildscript {
    val kotlinVersion = "1.2.0-rc-39"

    repositories {
        mavenCentral()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap-1.2")
    }
    dependencies {
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

plugins {
    java
    /*
        We use beta version of Kotlin 1.2 from a specific repo.
        This is the reason why we need the 'kotlin-gradle-plugin' in a buildScript section.
        To switch back to a stable Kotlin 1.1 we need:
            - remove the "http://dl.bintray.com/kotlin/kotlin-eap-1.2" repo;
            - remove the 'kotlin-gradle-plugin' plugin from the buildScript;
            - replace the 'kotlin' plugin with the the 'org.jetbrains.kotlin.jvm' in the apply section;
            - uncomment the line bellow.
    */
    // id("org.jetbrains.kotlin.jvm") version "1.1.51"
}

val hamcrestVersion by project
val junitJupiterVersion by project

subprojects {
    repositories {
        mavenCentral()
        maven(url = "http://dl.bintray.com/kotlin/kotlin-eap-1.2")
    }

    apply {
        plugin("java")
        plugin("kotlin")
        plugin("checkstyle")
        plugin("findbugs")
        plugin("pmd")
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
        testCompileOnly("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jre8")
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
}