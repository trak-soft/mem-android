package com.trak.mem.scene.play

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trak.mem.common.model.OptionType
import com.trak.mem.scene.play.model.Card
import com.trak.mem.scene.play.model.CardState
import kotlinx.coroutines.launch

class PlayViewModel(
    val mode:  OptionType.Mode,
    val tint: Color = Color.Black,
) : ViewModel() {

    private val _cards = mutableStateOf(listOf<Card>())
    val cards: MutableState<List<Card>> = _cards

    private val _timeLeft = mutableStateOf(mode.timeLimit?.toLong())
    val timeLeft: MutableState<Long?> = _timeLeft

    private val _clicksLeft = mutableStateOf(mode.clickLimit)
    val clicksLeft: MutableState<Int?> = _clicksLeft
    init
    {
        _cards.value = mutableListOf<Card>().apply {
            for (i in 0 until mode.numOfGroup)
                for (j in 0 until mode.groupLength) {
                    add(Card(j))
                }
        }.toList()
    }

    fun onCardClick(index: Int){
        viewModelScope.launch {
            val card = _cards.value.toMutableList().toList()
            when( card[index].iconState) {
                CardState.FACE_DOWN -> {
                    card[index].iconState = CardState.FACE_UP
                }
                else -> {}
            }
            _cards.value  = card
        }
    }
}