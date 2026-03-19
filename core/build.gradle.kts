plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.tru.core"
    compileSdk = 36


    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add("version")
    productFlavors {
        create("dev") {
            dimension = "version"
        }

        create("live") {
            dimension = "version"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    api("androidx.core:core-splashscreen:1.0.1")
    api("androidx.appcompat:appcompat-resources:1.7.1")
    api("androidx.activity:activity-compose:1.10.1")
    api("androidx.constraintlayout:constraintlayout-compose:1.1.1")

    api("com.google.protobuf:protobuf-javalite:4.31.1")

    api("androidx.core:core-ktx:1.16.0")
    api("androidx.appcompat:appcompat:1.7.1")
    api("com.google.android.material:material:1.12.0")
    testApi("junit:junit:4.13.2")
    androidTestApi("androidx.test.ext:junit:1.2.1")
    androidTestApi("androidx.test.espresso:espresso-core:3.6.1")
    androidTestApi(platform("androidx.compose:compose-bom:2025.06.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Debug
    debugApi("androidx.compose.ui:ui-tooling")
    debugApi("androidx.compose.ui:ui-test-manifest")


    //lifecycle-process
    api("androidx.lifecycle:lifecycle-process:2.9.1")

    // Crypto
    api("androidx.security:security-crypto:1.1.0-alpha06")

    // Lottie
    api("com.airbnb.android:lottie-compose:6.6.7")

    // jetpack compose bom
    api(platform("androidx.compose:compose-bom:2025.06.01"))
    api("androidx.compose.ui:ui")
    api("androidx.compose.foundation:foundation")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.material3:material3")

    // Android Studio Preview support
    api("androidx.compose.ui:ui-tooling-preview")
    debugApi("androidx.compose.ui:ui-tooling")

    // Accompanist
    api("com.google.accompanist:accompanist-permissions:0.37.3")

    // Navigation
    api("androidx.navigation:navigation-compose:2.9.1")

    // Lifecycle
    val lifecycle = "2.9.1"
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle")
    api("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
    api("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle")
    api("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle")
    api("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle")
    api("androidx.lifecycle:lifecycle-common-java8:$lifecycle")
    api("androidx.lifecycle:lifecycle-service:$lifecycle")

    // Data Store
    api("androidx.datastore:datastore-preferences:1.1.7")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-compiler:2.57.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    ksp("androidx.hilt:hilt-compiler:1.3.0")


    // Timber
    api("com.jakewharton.timber:timber:5.0.1")

    // Coroutines
    val coroutines = "1.10.2"
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutines")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")


    // play-services
    api("com.google.android.gms:play-services-base:18.7.2")
    api("com.google.android.gms:play-services-location:21.3.0")
    api("com.google.android.gms:play-services-code-scanner:16.1.0")
    api("com.google.android.gms:play-services-maps:19.2.0")
    api("com.google.android.play:app-update-ktx:2.1.0")
    api("com.google.android.play:app-update:2.1.0")


    // Gson
    api("com.google.code.gson:gson:2.13.1")

    // Retrofit
    val retrofit = "3.0.0"
    api("com.squareup.retrofit2:retrofit:$retrofit")
    api("com.squareup.retrofit2:converter-gson:$retrofit")

    // okhttp bom
    api(platform("com.squareup.okhttp3:okhttp-bom:5.1.0"))
    api("com.squareup.okhttp3:okhttp")
    api("com.squareup.okhttp3:logging-interceptor")

    // Coil
    api("io.coil-kt:coil-compose:2.7.0")

    // firebase bom
    api(platform("com.google.firebase:firebase-bom:33.16.0"))
    api("com.google.firebase:firebase-crashlytics")
    api("com.google.firebase:firebase-messaging-ktx")
    api("com.google.firebase:firebase-analytics")
    api("com.google.firebase:firebase-config-ktx")

    // JSON library
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    //pluto
    val plutoVersion = "3.0.1"
    debugApi("com.androidpluto:pluto:$plutoVersion")
    releaseApi("com.androidpluto:pluto-no-op:$plutoVersion")
    debugApi("com.androidpluto.plugins:bundle-core:$plutoVersion")
    releaseApi("com.androidpluto.plugins:bundle-core-no-op:$plutoVersion")


    // Lingver
    api("com.github.YarikSOffice:lingver:1.3.0")


    // Leakcanary
    debugApi("com.squareup.leakcanary:leakcanary-android:2.14")
}