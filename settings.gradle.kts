pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            library("jakarta-activation", "jakarta.activation:jakarta.activation-api:1.2.1")
        }
    }
}


rootProject.name = "free-market"
include(":app")
include(":network")
include(":ui-components")
