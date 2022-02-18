package com.trak.mem.scene.play

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
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

    val cards = mutableStateListOf<Card>()
    private val _groupSolved = mutableStateOf(0)
    val groupSolved: MutableState<Int> = _groupSolved


    private val _timeLeft = mutableStateOf(mode.timeLimit?.toLong())
    val timeLeft: MutableState<Long?> = _timeLeft

    private val _clicksLeft = mutableStateOf(mode.clickLimit)
    val clicksLeft: MutableState<Int?> = _clicksLeft

    private val actives = mutableListOf<Int>()
    init {
        groupSolved.value = 0
        for (i in 0 until mode.groupLength)
            for (j in 0 until mode.numOfGroup)
                cards.add(Card(j))
    }

    fun onEvent(event: PlayScreenEvent) {
        when(event) {
            is PlayScreenEvent.CardClick -> onCardClick(event.index)
        }
    }

    private fun onCardClick(index: Int) {
        viewModelScope.launch {
            if (groupSolved.value < mode.numOfGroup)
                when(cards[index].state) {
                    CardState.FACE_UP -> {}
                    CardState.FACE_DOWN -> {
                        clicksLeft.value?.let {
                            if (it > 0)
                                addToActive(index)
                        } ?: run {
                            addToActive(index)
                        }
                    }
                    CardState.SOLVED -> {}
                }
        }
    }

    private fun addToActive(index:Int) {
        clicksLeft.value?.let { clicks ->
            if (clicks > 0)
                clicksLeft.value = clicks - 1
        }
        if (actives.size > 1 && cards[actives[actives.size - 2]].icon != cards[actives[actives.size - 1]].icon) {
            for (active in actives)
                cards[active] = Card(cards[active].icon,CardState.FACE_DOWN)
            actives.clear()
        } else if (actives.size == mode.groupLength) {
            for (active in actives)
                cards[active] = Card(cards[active].icon,CardState.SOLVED)
            _groupSolved.value = _groupSolved.value + 1
            actives.clear()
        }
        actives.add(index)
        cards[index] = Card(cards[index].icon,CardState.FACE_UP)
    }
}