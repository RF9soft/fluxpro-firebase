plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.rf9.tips"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rf9.tips"
        minSdk = 24
        targetSdk = 33
        versionCode = 19
        versionName = "3.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }
    buildFeatures {
        viewBinding =true ;
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
}


dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-messaging:23.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("com.android.billingclient:billing:6.1.0")
    implementation ("androidx.multidex:multidex:2.0.1")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
}