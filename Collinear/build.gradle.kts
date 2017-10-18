tasks {
    "zip"(type = Zip::class) {
        baseName = project.name.toLowerCase()
        from("src/main/java")
        exclude("LineSegment.java")
        include("*")
    }
}