plugins {

    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "1.6.10"
    id("kotlin-parcelize")

}
android {
    namespace = "com.example.news"
    compileSdk = 33

    defaultConfig {
        buildConfigField(
            "String",
            "GUARDIAN_API_BASE_URL",
            project.property("guardianApiBaseUrl").toString()
        )
        buildConfigField("String", "GUARDIAN_API_KEY",
            "\"${project.property("guardianApiKey")}\"")

        applicationId = "com.example.news"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildTypes {

            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = "17"
        }
        buildFeatures {
            compose = true
            buildConfig = true

        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.4.3"
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }

    dependencies {

        implementation(libs.coreKtx)
        implementation(libs.lifecycleRuntimeKtx)
        implementation(libs.activityCompose)
        implementation(platform(libs.composeBom))
        implementation(libs.bundles.ui.compose)
        implementation(libs.material3)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidxTestExtJUnit)
        androidTestImplementation(libs.espressoCore)
        androidTestImplementation(platform(libs.composeBom))
        androidTestImplementation(libs.uiTestJUnit4)
        debugImplementation(libs.uiTooling)
        debugImplementation(libs.uiTestManifest)
        implementation(libs.navigation.compose)
        implementation(libs.bundles.koin)
        implementation(libs.bundles.ktor)
        implementation(libs.kotlinx.serialization.json)
        implementation(libs.coilCompose)
        implementation(libs.bundles.paging)
        implementation(libs.bundles.datastore)
        implementation(libs.bundles.runtime)
        implementation(libs.bundles.room)
        ksp(libs.roomCompiler)

    }
}