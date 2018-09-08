Coursera Algorithms course homework
=============

[![TravisCI Build](https://travis-ci.org/hiper2d/spring-kotlin-angular-demo.svg)](https://travis-ci.org/hiper2d/coursera-algorithms)

### Technology stack
* Gradle 4.10
* jUnit 5
* Kotlin 1.2.61 in tests

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
* Week 1: [Percolation](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html), [checklist](http://coursera.cs.princeton.edu/algs4/checklists/percolation.html)
* Week 2: [Queues](http://coursera.cs.princeton.edu/algs4/assignments/queues.html), [checklist](http://coursera.cs.princeton.edu/algs4/checklists/queues.html)
* Week 3: [Collinear](http://coursera.cs.princeton.edu/algs4/assignments/collinear.html), [checklist](http://coursera.cs.princeton.edu/algs4/checklists/collinear.html)
* Week 4: [8puzzle](http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html), [checklist](http://coursera.cs.princeton.edu/algs4/checklists/8puzzle.html)
* Week 5: [KdTree](http://coursera.cs.princeton.edu/algs4/assignments/kdtree.html), [checklist](http://coursera.cs.princeton.edu/algs4/checklists/kdtree.html)
##### Part 2
* Week 5: [WordNet](http://coursera.cs.princeton.edu/algs4/assignments/wordnet.html), [checklist](http://coursera.cs.princeton.edu/algs4/checklists/wordnet.html)