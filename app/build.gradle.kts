plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}
android {
    namespace = "com.example.note"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.note"
        minSdk = 33
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



    implementation ("androidx.appcompat:appcompat:1.7.0")

    // compose dependencies
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation ("androidx.navigation:navigation-compose:2.8.9")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    // coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")

    // dagger - hilt
    implementation ("com.google.dagger:hilt-android-gradle-plugin:2.55")
    implementation ("com.google.dagger:hilt-android:2.55")
    ksp ("com.google.dagger:hilt-android-compiler:2.52")
    ksp ("androidx.hilt:hilt-compiler:1.2.0")

    // room
    implementation ("androidx.room:room-runtime:2.6.1")
    ksp ("androidx.room:room-compiler:2.6.1")

    // kotlin extensions and coroutines support for room
    implementation ("androidx.room:room-ktx:2.6.1")

    // material 3
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation ("androidx.compose.material:material:1.7.8")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // live data
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    // for tab layout draggable
    implementation ("androidx.compose.runtime:runtime-livedata:1.7.8")

    // data store
    implementation("androidx.datastore:datastore-preferences:1.1.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")

    // splash API
    implementation ("androidx.core:core-splashscreen:1.0.1")

    // lottie
    implementation("com.airbnb.android:lottie-compose:6.1.0")
}