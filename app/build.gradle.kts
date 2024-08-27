import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    alias(libs.plugins.navigationSafeArgs)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.example.kekodweekoneproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kekodweekoneproject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    // ViewModel
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.activity.ktx)

    // LiveData
    implementation(libs.lifecycle.livedata)

    // Picasso
    implementation(libs.picasso)

    // RecyclerView for Search
    implementation(libs.recyclerview)

    // Lottie
    implementation(libs.lottie)

    // Animated Svg for Splash
    implementation(libs.animated.svg.view)

    // Room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Detekt
    detektPlugins(libs.detekt)
}

detekt {
    config.setFrom(file("$rootDir/detekt/detektConfig.yml"))
    source.from(files("src/main/kotlin", "src/test/kotlin"))
    parallel = true
    autoCorrect = true
    buildUponDefaultConfig = true
}