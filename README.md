Coursera Algorithms course homework
=============

[![TravisCI Build](https://travis-ci.org/hiper2d/spring-kotlin-angular-demo.svg)](https://travis-ci.org/hiper2d/coursera-algorithms)

### Technology stack
* Gradle 4
* jUnit 5
* Kotlin in tests

### Highlights
* Each homework is in separate Gradle module
* Gradle tasks for running Stylecheck, Findbugs, PMD
* Gradle task for distributing module's sources into Zip for uploading to Coursera

### Specific Gradle tasks
> use `./gradlew` instead of `gradle` if you didn't installed `gradle`
```bash
# Build specific module's sources into zip
gradle module_name:zip
# Analyze specific module with Stylecheck, Findbugs and PMD
gradle module_name:analyze
```
### Specifications
##### Part 1
* Week 1: [Percolation](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)
* Week 2: [Queues](http://coursera.cs.princeton.edu/algs4/assignments/queues.html)
* Week 3: [Collinear](http://coursera.cs.princeton.edu/algs4/assignments/collinear.html)
* Week 4: [8puzzle](http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html)
