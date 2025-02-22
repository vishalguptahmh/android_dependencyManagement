# android_dependencyManagement
android_dependencyManagement will help to upgrade your android project from picking file from server and keep it updated


## Usage 

add below code in settings.gradle file in the last

```kotlin
apply(from = "https://raw.githubusercontent.com/vishalguptahmh/android_dependencyManagement/refs/heads/master/dependency-sync.gradle.kts")
```

if you want that whenever you click on build then libs.version.toml file will get updated then add below code just below the above line

```kotlin
gradle.projectsEvaluated {
    rootProject.subprojects.forEach { subproject ->
        if (subproject.plugins.hasPlugin("com.android.application") ||
            subproject.plugins.hasPlugin("com.android.library")) {
            subproject.tasks.matching { it.name == "preBuild" }.configureEach {
                // Attach the updateLibsToml task from the root project
                dependsOn(rootProject.tasks.findByName("updateLibsTomlvishalguptahmh"))
            }
        }
    }
}
```

if you don't want to add above gradle code and want to get latest updated version of libs.version file then run below command in terminal . it will sync the latest lib versions

```sh

./gradlew updateLibsTomlvishalguptahmh
```

