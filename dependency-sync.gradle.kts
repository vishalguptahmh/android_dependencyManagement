import java.net.URL
import java.io.File

gradle.afterProject {
    if (this == rootProject) {
        val tomlFile = File(rootDir, "gradle/libs.versions.toml")
        val tomlUrl =
            "https://raw.githubusercontent.com/vishalguptahmh/android_dependencyManagement/refs/heads/master/libs.versions.toml"

        fun downloadTomlFile() {
            if (!tomlFile.exists()) {
                println("libs.versions.toml not found, downloading from vishalguptahmh GitHub...")
                tomlFile.writeText(URL(tomlUrl).readText())
                println("Downloaded libs.versions.toml successfully from vishalguptahmh github!")
            }
        }

        downloadTomlFile()


        if (gradle.rootProject.tasks.findByName("updateLibsToml") == null) {
            tasks.register("updateLibsTomlvishalguptahmh") {
                doLast {
                    println("Fetching latest libs.versions.toml from vishalguptahmh GitHub...")
                    tomlFile.writeText(URL(tomlUrl).readText())
                    println("Updated libs.versions.toml successfully from vishalguptahmh!")
                }
            }
        }
    }
}