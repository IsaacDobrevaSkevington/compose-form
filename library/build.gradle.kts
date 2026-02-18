import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.URI

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    id("maven-publish")
    alias(libs.plugins.vanniktech.maven.publish)
    alias(libs.plugins.dokka)
    signing
}

kotlin {
    withSourcesJar(publish = true)
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    )

    jvm()

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.runtime)
            implementation(libs.foundation)
            implementation(libs.material3)
            implementation(libs.ui)
            implementation(libs.components.resources)
            implementation(libs.ui.tooling.preview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.kotlinx.datetime)
            api(libs.filekit.core)
            api(libs.filekit.dialogs)
            api(libs.filekit.dialogs.compose)
            implementation(libs.filekit.coil)
            implementation(libs.kotlin.reflect)
        }
    }
}

android {
    namespace = "com.idscodelabs.compose_form"
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()

    defaultConfig {
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    publishing {
        singleVariant("release") {
            withJavadocJar()
            withSourcesJar()
        }

        singleVariant("debug") {
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI("https://maven.pkg.github.com/IsaacDobrevaSkevington/compose-form")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
}

dokka {
    moduleName.set("Compose Form Core")
    dokkaPublications.html {
        suppressInheritedMembers.set(true)
        failOnWarning.set(true)
        outputDirectory.set(File("${rootProject.projectDir}/docs/dokka/core"))
    }
    dokkaSourceSets.commonMain {
        samples.from("${rootProject.projectDir}/composeApp/src/commonMain/kotlin/com/idscodelabs/compose_form/examples")
    }
}
signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(
        signingKey ?: System.getenv("GPG_KEY"),
        signingPassword ?: System.getenv("GPG_PASSWORD"),
    )
}

mavenPublishing {
    coordinates(
        groupId = "io.github.idscodelabs",
        artifactId = "compose-form",
        version = version.toString(),
    )

    pom {
        name.set("Compose Form")
        description.set("Multiplatform form library for Compose")
        inceptionYear.set("2025")
        url.set("https://github.com/IsaacDobrevaSkevington/compose-form")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        developers {
            developer {
                id.set("Isaac-Dobreva-Skevington")
                name.set("Isaac-Dobreva-Skevington")
                email.set("-")
            }
        }

        scm {
            url.set("https://github.com/IsaacDobrevaSkevington/compose-form")
        }
    }

    // Configure publishing to Maven Central
    //publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}
