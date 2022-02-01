package com.trak.mem.scene.play

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.trak.mem.R
import com.trak.mem.common.component.model.OptionType

class PlayViewModel(
    val mode:  OptionType.Mode,
    val tint: Color = Color.Black,
) : ViewModel() {

    private val _icons = mutableStateOf(listOf<Int>())
    val icons: MutableState<List<Int>> = _icons

    private val _timeLeft = mutableStateOf(mode.timeLimit?.toLong())
    val timeLeft: MutableState<Long?> = _timeLeft

    private val _clicksLeft = mutableStateOf(mode.clickLimit)
    val clicksLeft: MutableState<Int?> = _clicksLeft

    init {
        icons.value = mutableListOf<Int>().apply {
            for (i in 1..(mode.groupLength * mode.numOfGroup)) {
                add(R.drawable.ic_brain)
            }
        }.toList()
    }
}