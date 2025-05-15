plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.vocabit"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.vocabit"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"http://192.168.116.179:8080/learning-english/\"")


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
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
    
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.adapter.rxjava3)
    implementation (libs.rxjava3.retrofit.adapter)

    // lombok
    compileOnly (libs.lombok)
    annotationProcessor (libs.lombok)

    //Dagger
    implementation (libs.dagger)
    annotationProcessor (libs.dagger.compiler)

    //RxJava
    implementation (libs.rxjava)

    //Timber
    implementation (libs.timber)

    //Toasty
    implementation (libs.toasty)

// ViewModel
    implementation (libs.lifecycle.viewmodel)

    // LiveData
    implementation (libs.lifecycle.livedata)

    implementation (libs.lifecycle.extensions)

    // optional - RxJava3 support for Room
    implementation (libs.room.rxjava3)

    //RxAndroid
    implementation (libs.rxandroid)
    //RxJava
    implementation (libs.rxjava)
    //hash id
    implementation (libs.hashids)

    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    // Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)

    implementation(libs.cloudinary.android)
    implementation ("com.auth0.android:jwtdecode:2.0.1")


}