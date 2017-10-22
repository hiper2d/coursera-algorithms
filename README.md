Coursera algorithms homework
=============
[![TravisCI Build](https://travis-ci.org/hiper2d/spring-kotlin-angular-demo.svg)](https://travis-ci.org/hiper2d/coursera-algorithms)

#####Technology stack
* Gradle 4
* jUnit 5
* Kotlin in tests
* Build-in Gralde plugins for Stylecheck, Findbugs, PMD

#####Specific Gradle tasks
> use `./gradlew` instead of `gradle` if you didn't installed `gradle`
```bash
# Build specific module's sources into zip
gradle module_name:zip
# Analyze specific module with Stylecheck, Findbugs and PMD
gradle module_name:analyze