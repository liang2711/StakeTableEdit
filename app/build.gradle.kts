plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.tianchi.staketableedit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tianchi.staketableedit"
        minSdk = 29
        targetSdk = 35
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
        debug {
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
    buildFeatures {
        compose = true
    }
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/NOTICE")
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
    implementation(libs.material.icons.extended)
    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    // navigation
    implementation(libs.hilt.navigation.compose)
    implementation(libs.compose.navigation)
    // serialization
    implementation(libs.kotlin.serialization)
    // datastore
    implementation(libs.datastore)
    // ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging)
    // coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor3)
    // work
    implementation(libs.work.runtime.ktx)
    // room
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.21")

//    implementation(files("libs/poi-5.3.0.jar"))
    implementation("org.apache.poi:poi-ooxml:5.2.3") {
        exclude(group = "org.apache.logging.log4j", module = "log4j-api")
    }  // 主要的poi库
    implementation("org.apache.poi:poi:5.2.3") {
        exclude(group = "org.apache.logging.log4j", module = "log4j-api")
    } // 用于处理Excel文件（.xlsx）
    implementation("org.apache.xmlbeans:xmlbeans:5.0.2")  // Apache Commons IO
//    implementation ("org.apache.logging.log4j:log4j-api:2.14.1") // Log4j API
//    implementation ("org.apache.logging.log4j:log4j-core:2.14.1") // Log4j Core

//    implementation("org.apache.logging.log4j:log4j-core:2.17.1")  // 或其他最新版本
    implementation("org.apache.logging.log4j:log4j-api:2.17.1")   // 或其他最新版本

}