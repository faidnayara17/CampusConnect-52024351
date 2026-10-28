import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    // AGP 9+: pengganti androidTarget() / com.android.library untuk modul KMP
    alias(libs.plugins.android.kotlin.multiplatform.library)
}

kotlin {
    // Target Android (menghasilkan source set androidMain)
    android {
        namespace = "com.kharisma.campusconnect.shared"
        compileSdk = 37
        minSdk = 26

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    // Target Desktop (menghasilkan source set desktopMain) - ditambahkan manual
    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    // Target iOS sengaja tidak dikonfigurasi (opsional, tidak wajib pada praktikum ini)

    sourceSets {
        commonMain.dependencies {
            // Hanya library multiplatform yang boleh ditambahkan di sini
        }
    }
}
