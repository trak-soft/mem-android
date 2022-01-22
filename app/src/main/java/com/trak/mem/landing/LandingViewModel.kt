package com.trak.mem.landing

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.trak.mem.R
import com.trak.mem.landing.component.OptionType

class LandingViewModel : ViewModel(){
    private val _title = mutableStateOf("memory")
    val title: State<String> = _title

    private val _image = mutableStateOf(R.drawable.ic_brain)
    val image : State<Int> = _image

    private val _options = mutableStateOf(setOf(
        OptionType.Add,
        OptionType.Mode(2,  true,9, null, null),
        OptionType.Mode(2,  true,9, null, 3),
        OptionType.Mode(2,  true,9, 67, null),
    ))
    val options: State<Set<OptionType>> = _options
}