package com.trak.mem.scene.edit

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.trak.mem.R

/**
 * edit screen view model
 */
class EditViewModel(
    val title: String = "create",
    val icon: Int = R.drawable.ic_add_game,
    val tint: Color = Color.Black,
) : ViewModel()