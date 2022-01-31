package com.trak.mem.scene.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.trak.mem.R
import com.trak.mem.common.component.model.OptionType

/**
 * Home view model
 */
class HomeViewModel(
    val title: String = "memory",
    val icon: Int = R.drawable.ic_brain,
    val tint: Color = Color.Black,
    val optionColor: Color =  Color.Black
) : ViewModel(){
    private val _options = mutableStateOf(setOf(
        OptionType.Add,
        OptionType.Mode(3, true,9, null, null),
        OptionType.Mode(2,  true,9, null, 3),
        OptionType.Mode(2,  true,10, 67, null),
    ))
    val options: State<Set<OptionType>> = _options
}