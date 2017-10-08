plugins {
    java
}

subprojects {
    apply {
        plugin("java")
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
    }

    repositories {
        jcenter()
    }
}