
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.6.10"

}

android {
    namespace = "com.example.news"
    compileSdk = 33

    defaultConfig {
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
    implementation ("io.insert-koin:koin-androidx-compose:3.4.0")
    implementation("io.ktor:ktor-client-okhttp:2.3.0")
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation ("io.ktor:ktor-client-json:2.3.0")
    implementation ("io.ktor:ktor-client-serialization:2.3.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0")
    implementation ("io.ktor:ktor-client-logging:2.3.0")
    implementation("io.ktor:ktor-server-cors:2.3.0")
    implementation ("io.ktor:ktor-client-gson:2.3.0")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0" )




}}