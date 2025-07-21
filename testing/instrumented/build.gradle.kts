plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.instrumented"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    defaultConfig {
        testInstrumentationRunner =  "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.junit.ktx)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation(libs.androidx.rules)
    // Optional -- UI testing with Espresso
    androidTestImplementation(libs.androidx.espresso.core)
    // Optional -- UI testing with UI Automator
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0")
    // Optional -- UI testing with Compose
    androidTestImplementation(libs.ui.test.junit4)

    androidTestImplementation("androidx.test.ext:truth:1.6.0")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")

}