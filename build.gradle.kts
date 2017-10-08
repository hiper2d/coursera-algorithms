plugins {
    application
    base
}

application {
    mainClassName = "Percolation"
}

base {
    archivesBaseName = "algs"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compile(files("lib/algs4.jar"))
    testCompile("junit:junit:4.12")
}

repositories {
    jcenter()
}