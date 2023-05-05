plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
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
        buildConfigField("String", "GUARDIAN_API_KEY", "\"${project.property("guardianApiKey")}\"")

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
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
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
        implementation(libs.ui)
        implementation(libs.uiGraphics)
        implementation(libs.uiToolingPreview)
        implementation(libs.material3)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidxTestExtJUnit)
        androidTestImplementation(libs.espressoCore)
        androidTestImplementation(platform(libs.composeBom))
        androidTestImplementation(libs.uiTestJUnit4)
        debugImplementation(libs.uiTooling)
        debugImplementation(libs.uiTestManifest)
        implementation(libs.navigation.compose)
        implementation(libs.koin.core)
        implementation(libs.koin.android)
        implementation(libs.koin.androidx.compose)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.ktor.client.core)
        implementation(libs.ktor.client.cio)
        implementation(libs.ktor.client.json)
        implementation(libs.ktor.client.serialization)
        implementation(libs.ktor.serialization.kotlinx.json)
        implementation(libs.ktor.client.logging)
        implementation(libs.ktor.server.cors)
        implementation(libs.ktor.client.gson)
        implementation(libs.ktor.client.content.negotiation)
        implementation(libs.kotlinx.serialization.json)
        implementation(libs.coilCompose)
        implementation(libs.paging.runtime)
        testImplementation(libs.paging.common)
        implementation(libs.paging.rxjava2)
        implementation(libs.paging.rxjava3)
        implementation(libs.paging.guava)
        implementation(libs.paging.compose)
        implementation (libs.datastore.preferences)
        implementation(libs.androidx.datastore.preferences.rxjava2)
        implementation(libs.androidx.datastore.preferences.rxjava3)
        implementation(libs.androidx.datastore.preferences.core)
        implementation(libs.androidx.runtime)
        implementation(libs.androidx.runtime.livedata)
        implementation(libs.androidx.runtime.rxjava2)

    }
}