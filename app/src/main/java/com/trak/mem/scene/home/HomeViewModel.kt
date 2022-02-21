package com.trak.mem.scene.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.trak.mem.R
import com.trak.mem.common.model.OptionType

/**
 * Home view model
 */
class HomeViewModel(
    val title: String = "memory",
    val icon: Int = R.drawable.ic_brain,
) : ViewModel() {
    private val _options = mutableStateOf(setOf(
        OptionType.ADD,
        OptionType.MODE(3, true,9, null, null),
        OptionType.MODE(2,  true,9, null, 10),
        OptionType.MODE(2,  true,10, 67, null),
        OptionType.MODE(3,  true,14, 67, null),
        OptionType.MODE(4,  true,10, 67, null),
        OptionType.MODE(2,  true,15, 67, null),
        OptionType.MODE(4,  true,8, 67, null),
        OptionType.MODE(3,  true,7, 67, 70),
        OptionType.MODE(2,  true,3, null, 70),
    ))
    val options: State<Set<OptionType>> = _options
}