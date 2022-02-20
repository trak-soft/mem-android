package com.trak.mem.scene.play

import android.os.CountDownTimer
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

    private val _timeLeft = mutableStateOf(mode.timeLimit?.let { return@let it * MILLISECOND })
    val timeLeft: MutableState<Long?> = _timeLeft

    private val _clicksLeft = mutableStateOf(mode.clickLimit)
    val clicksLeft: MutableState<Int?> = _clicksLeft

    private var timer: CountDownTimer? = mode.timeLimit?.let {
        object : CountDownTimer(it * MILLISECOND, TIME_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished
            }
            override fun onFinish() {
                _timeLeft.value = 0
            }
        }
    }

    private val actives = mutableListOf<Int>()

    init {
        _groupSolved.value = 0
        for (i in 0 until mode.groupLength)
            for (j in 0 until mode.numOfGroup)
                cards.add(Card(j))
       timer?.start()

    }

    fun onEvent(event: PlayScreenEvent) {
        when(event) {
            is PlayScreenEvent.CardClick -> onCardClick(event.index)
        }
    }

    private fun onCardClick(index: Int) {
        viewModelScope.launch {
            if (_groupSolved.value < mode.numOfGroup) {
                when (cards[index].state) {
                    CardState.FACE_UP -> {}
                    CardState.FACE_DOWN -> {
                        when {
                            mode.timeLimit != null && mode.clickLimit != null -> {
                                clicksLeft.value?.let { clicks ->
                                    timeLeft.value?.let { time ->
                                        if (clicks > 0 && time > 0)
                                            addToActive(index)
                                        if (clicks == 1)
                                            timer?.cancel()
                                    }
                                }
                            }
                            mode.timeLimit != null -> {
                                timeLeft.value?.let { time ->
                                    if (time > 0)
                                        addToActive(index)
                                }
                            }
                            mode.clickLimit != null -> {
                                clicksLeft.value?.let { clicks ->
                                    if (clicks > 0)
                                        addToActive(index)
                                }
                            }
                            else -> {
                                addToActive(index)
                            }
                        }
                    }
                    CardState.SOLVED -> {}
                }
            if (_groupSolved.value == mode.numOfGroup)
                timer?.cancel()
            }
        }
    }

    private fun addToActive(index:Int) {
        clicksLeft.value?.let { clicks ->
            if (clicks > 0)
                clicksLeft.value = clicks - 1
        }

        if (actives.size > 1 &&
                cards[actives[actives.size - 2]].icon != cards[actives[actives.size - 1]].icon) {
            for (active in actives)
                cards[active] = Card(cards[active].icon,CardState.FACE_DOWN)
            actives.clear()
        }

        actives.add(index)
        cards[index] = Card(cards[index].icon,CardState.FACE_UP)

        if (!(actives.size > 1 &&
                cards[actives[actives.size - 2]].icon != cards[actives[actives.size - 1]].icon) &&
                actives.size == mode.groupLength) {
            for (active in actives)
                cards[active] = Card(cards[active].icon,CardState.SOLVED)
            _groupSolved.value = _groupSolved.value + 1
            actives.clear()
        }
    }

    companion object {
        const val MILLISECOND: Long = 1000
        const val TIME_INTERVAL: Long = 10
    }
}