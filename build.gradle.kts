plugins {
    id("java")
}

val pluginVersion: String by project
val pluginDescription = "Optimize texture pack management on bungee/velocity to never double send the same pack."
group = "io.th0rgal.packsmanager"
version = pluginVersion

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://mvn.exceptionflug.de/repository/exceptionflug-public/")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("dev.simplix:protocolize-api:2.3.0")
    compileOnly("net.md-5:bungeecord-api:1.20-R0.1-SNAPSHOT")
    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
}



tasks {
    jar {
        filesMatching(arrayOf("bungee.yml", "velocity-plugin.json").asIterable()) {
            expand(mapOf("version" to pluginVersion, "description" to pluginDescription))
        }
    }

    compileJava.get().dependsOn(clean)
}
