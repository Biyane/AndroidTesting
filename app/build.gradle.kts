plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.myapplication2"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.myapplication2"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            buildConfigField()
        }

        debug {
            isMinifyEnabled = false
            versionNameSuffix = ".debug"
            applicationIdSuffix = ".biyane"
            isDebuggable = true

            //            buildConfigField()

        }

        create("staging") {
            isMinifyEnabled = false
            isDebuggable = false
            versionNameSuffix = ".staging"
        }
    }

    flavorDimensions += listOf("environment", "testing")
    productFlavors {
        create("activ") {
            dimension = "environment"
        }

        create("kcell") {
            dimension = "testing"
        }

        create("Halyk") {
            dimension = "environment"
        }
        create("Kaspi") {
            dimension = "testing"
        }
    }

    sourceSets {
        getByName("activ") {
            java.srcDir("src/activ/java")
            res.srcDir("src/activ/src")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.junit.ktx)


    testImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.junit.ktx)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(libs.androidx.ui.test.junit4)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    testImplementation(libs.junit)
    // Optional -- Robolectric environment
    testImplementation(libs.androidx.core)
    // Optional -- Mockito framework
    testImplementation(libs.mockito.core)
    // Optional -- mockito-kotlin
    testImplementation(libs.mockito.kotlin)
    // Optional -- Mockk framework
    testImplementation(libs.mockk)

    testImplementation(libs.robolectric)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation(kotlin("test"))

//    activImplementation("androidx.compose.ui:ui-test-junit4")

    //    Most common used
//        // Core library
//        androidTestImplementation "androidx.test:core:$androidXTestVersion0"
//
//        // AndroidJUnitRunner and JUnit Rules
//        androidTestImplementation "androidx.test:runner:$testRunnerVersion"
//        androidTestImplementation "androidx.test:rules:$testRulesVersion"
//
//        // Assertions
//        androidTestImplementation "androidx.test.ext:junit:$testJunitVersion"
//        androidTestImplementation "androidx.test.ext:truth:$truthVersion"
//
//        // Espresso dependencies
//        androidTestImplementation "androidx.test.espresso:espresso-core:$espressoVersion"
//        androidTestImplementation "androidx.test.espresso:espresso-contrib:$espressoVersion"
//        androidTestImplementation "androidx.test.espresso:espresso-intents:$espressoVersion"
//        androidTestImplementation "androidx.test.espresso:espresso-accessibility:$espressoVersion"
//        androidTestImplementation "androidx.test.espresso:espresso-web:$espressoVersion"
//        androidTestImplementation "androidx.test.espresso.idling:idling-concurrent:$espressoVersion"
//
//        // The following Espresso dependency can be either "implementation",
//        // or "androidTestImplementation", depending on whether you want the
//        // dependency to appear on your APK’"s compile classpath or the test APK
//        // classpath.
//        androidTestImplementation "androidx.test.espresso:espresso-idling-resource:$espressoVersion"
}