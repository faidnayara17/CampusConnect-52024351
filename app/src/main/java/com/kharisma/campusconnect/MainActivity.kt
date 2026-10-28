package com.kharisma.campusconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.kharisma.campusconnect.shared.CourseRepositoryImpl
import com.kharisma.campusconnect.ui.HomeScreen
import com.kharisma.campusconnect.ui.theme.CampusConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Sementara dibuat langsung di sini; akan dirapikan dengan DI pada Pertemuan 4
        val repository = CourseRepositoryImpl()
        setContent {
            CampusConnectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(daftarMK = repository.getAllCourses())
                }
            }
        }
    }
}