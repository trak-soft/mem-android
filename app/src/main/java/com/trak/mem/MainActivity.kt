package com.trak.mem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.trak.mem.scene.NavGraphs
import com.trak.mem.ui.theme.MemandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
        setContent {
            MemandroidTheme {
                val navController = rememberAnimatedNavController()
                BoxWithConstraints {
                    val width = constraints.maxWidth
                    val navHostEngine = rememberAnimatedNavHostEngine(
                        navHostContentAlignment = Alignment.TopCenter,
                        rootDefaultAnimations = RootNavGraphDefaultAnimations(
                            enterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = { width },
                                    animationSpec = tween(
                                        durationMillis = 700,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { -width },
                                    animationSpec = tween(
                                        durationMillis = 700,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            },
                            popEnterTransition = {
                                slideInHorizontally(
                                    initialOffsetX = { -width },
                                    animationSpec = tween(
                                        durationMillis = 700,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            },
                            popExitTransition = {
                                slideOutHorizontally(
                                    targetOffsetX = { width },
                                    animationSpec = tween(
                                        durationMillis = 700,
                                        easing = FastOutSlowInEasing
                                    )
                                )
                            }
                        ),
                    )
                    Surface(color = MaterialTheme.colors.background) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            engine = navHostEngine,
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}