package com.trak.mem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.ramcosta.composedestinations.DestinationsNavHost
import com.trak.mem.scene.NavGraph
import com.trak.mem.scene.NavGraphs
import com.trak.mem.scene.landing.LandingScreen
import com.trak.mem.scene.landing.LandingViewModel
import com.trak.mem.ui.theme.MemandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}