plugins {
    java
}

subprojects {
    apply {
        plugin("java")
        plugin("checkstyle")
        plugin("findbugs")
        plugin("pmd")
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
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    base {
        archivesBaseName = project.name.toLowerCase()
    }

    dependencies {
        compile(files("../lib/algs4.jar"))
        testCompile("junit:junit:4.12")
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
        jcenter()
    }
}