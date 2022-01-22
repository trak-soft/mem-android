package com.trak.mem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.trak.mem.landing.LandingScreen
import com.trak.mem.landing.LandingViewModel
import com.trak.mem.ui.theme.MemandroidTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LandingScreen(
                        viewModel = LandingViewModel()
                    )
                }
            }
        }
    }
}