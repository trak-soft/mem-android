package com.trak.mem.scene.play

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.trak.mem.R

class PlayViewModel(
    val title: String = "play",
    val icon: Int = R.drawable.ic_add_game,
    val tint: Color = Color.Black,
) : ViewModel()