import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.ustinovauliana.composestateandseapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ustinovauliana.composestateandseapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation ("com.google.android.libraries.maps:maps:3.1.0-beta")
    implementation ("com.google.maps.android:maps-v3-ktx:3.4.0")
    constraints {
        // Volley is a transitive dependency of maps
        implementation("com.android.volley:volley:1.2.1") {
            because("Only volley 1.2.0 or newer are available on maven.google.com")
        }
    }

    implementation ("androidx.appcompat:appcompat:1.7.0")
    implementation ("androidx.tracing:tracing:1.2.0")

    implementation ("androidx.compose.runtime:runtime")
    implementation ("androidx.compose.material:material")
    implementation ("androidx.compose.foundation:foundation")
    implementation ("androidx.compose.foundation:foundation-layout")
    implementation ("androidx.compose.animation:animation")
    implementation ("androidx.compose.ui:ui-tooling-preview")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
    implementation (libs.dagger.hilt.android)
    kapt (libs.dagger.hilt.compiler)

    implementation ("io.coil-kt:coil-compose:2.6.0")

    androidTestImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test:core:1.6.0")
    androidTestImplementation ("androidx.test:runner:1.6.0")
    androidTestImplementation ("androidx.test:rules:1.6.0")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.6.0")
    androidTestImplementation ("androidx.test.ext:junit-ktx:1.2.0")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.51.1")
}
