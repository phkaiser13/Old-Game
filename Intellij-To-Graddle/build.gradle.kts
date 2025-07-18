// build.gradle.kts
// Este arquivo descreve como construir nosso projeto. Ele define os plugins,
// as dependências (como JavaFX) e as configurações necessárias para compilar e executar.

plugins {
    // Plugin essencial para compilar código Kotlin para a JVM.
    kotlin("jvm") version "1.9.22"

    // Plugin que nos dá tarefas para criar e executar uma aplicação.
    application

    // Plugin oficial para simplificar o trabalho com JavaFX.
    id("org.openjfx.javafxplugin") version "0.1.0"
}

// Repositório de onde o Gradle baixará as dependências.
repositories {
    mavenCentral()
}

// Versão do Java que nosso projeto usa.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Configurações específicas do JavaFX.
javafx {
    version = "17.0.10" // Uma versão estável e recente do JavaFX.
    modules = listOf("javafx.controls", "javafx.fxml") // Módulos que usamos: controles de UI e FXML.
}

// Define a classe principal que contém o método main().
application {
    mainClass.set("com.pedrohenrique.tictactoe.Main")
}

// Informa ao Gradle onde encontrar nossos arquivos de código-fonte,
// já que usamos tanto Java quanto Kotlin.
sourceSets["main"].java.srcDirs("src/main/java", "src/main/kotlin")
