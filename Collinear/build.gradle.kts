tasks {
    getByName<Zip>("zip") {
        baseName = project.name.toLowerCase()
        from("src/main/java")
        exclude("LineSegment.java")
        include("*")
    }
}