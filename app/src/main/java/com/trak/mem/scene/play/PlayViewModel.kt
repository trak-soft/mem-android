package com.trak.mem.scene.play

import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trak.mem.common.model.OptionType
import com.trak.mem.scene.play.model.Card
import com.trak.mem.scene.play.model.CardState
import com.trak.mem.scene.play.model.GameState
import kotlinx.coroutines.launch

/**
 * @property mode - game mode
 * @property cards - list of cards
 * @property groupSolved - number of groupsolved
 * @property timeLeft - time left
 * @property clicksLeft - clicks left
 * @property state - game state
 * @property timer - count down timer
 */
class PlayViewModel(
    val mode: OptionType.MODE,
) : ViewModel() {

    val cards = mutableStateListOf<Card>()
    private val _groupSolved = mutableStateOf(0)
    val groupSolved: MutableState<Int> = _groupSolved

    private val _timeLeft = mutableStateOf(mode.timeLimit?.let { return@let it * MILLISECOND })
    val timeLeft: MutableState<Long?> = _timeLeft

    private val _clicksLeft = mutableStateOf(mode.clickLimit)
    val clicksLeft: MutableState<Int?> = _clicksLeft

    private val _state = mutableStateOf<GameState>(GameState.INIT)
    val state: MutableState<GameState> = _state

    private var timer: CountDownTimer? = mode.timeLimit?.let {
        object : CountDownTimer(it * MILLISECOND, TIME_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished
            }
            override fun onFinish() {
                _timeLeft.value = 0
                toOverState()
            }
        }
    }

    private val actives = mutableListOf<Int>()

    init {
        _groupSolved.value = 0
        for (i in 0 until mode.groupLength)
            for (j in 0 until mode.numOfGroup)
                cards.add(Card(j))
    }

    //events

    /**
     * event handler
     *
     * @param event - event to be handled
     */
    fun onEvent(event: PlayScreenEvent) {
        when (event) {
            is PlayScreenEvent.CardClick -> onCardClickEvent(event.index)
            PlayScreenEvent.Reset -> onResetEvent()
        }
    }

    /**
     * on reset events
     */
    private fun onResetEvent() {
        viewModelScope.launch {
            toInitState()
            toPlayState()
        }
    }

    /**
     * on card click event
     *
     * @param index of card clicked
     */
    private fun onCardClickEvent(index: Int) {
        viewModelScope.launch {
            if (_state.value == GameState.PLAY) {
                when (cards[index].state) {
                    CardState.FACE_UP -> {}
                    CardState.FACE_DOWN -> {
                        when {
                            mode.timeLimit != null && mode.clickLimit != null -> {
                                clicksLeft.value?.let { clicks ->
                                    timeLeft.value?.let { time ->
                                        if (clicks > 0 && time > 0)
                                            addToActive(index)
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
                if (_groupSolved.value == mode.numOfGroup ||
                    _clicksLeft.value == 0 ||
                    _timeLeft.value == 0L)
                    toOverState()
            }
        }
    }

    // change state

    /**
     * changes state to inti
     */
    private fun toInitState() {
        timer?.cancel()
        _clicksLeft.value = mode.clickLimit
        _timeLeft.value = mode.timeLimit?.let { return@let it * MILLISECOND }
        _groupSolved.value = 0
        for (index in 0 until cards.size) {
            cards[index] = cards[index].copy(state = CardState.FACE_DOWN)
        }

        _state.value = GameState.INIT
    }

    /**
     * changes state to preview
     */
    fun toPreviewState() {
        if (_state.value == GameState.INIT)
            _state.value = GameState.PREVIEW
    }

    /**
     * changes state to play
     */
    private fun toPlayState() {
        if (_state.value == GameState.INIT || _state.value == GameState.PREVIEW) {
            _state.value = GameState.PLAY
            timer?.start()
        }
    }

    /**
     * changes state to over
     */
    private fun toOverState() {
        if (_state.value == GameState.PLAY) {
            _state.value = GameState.OVER(won = _groupSolved.value == mode.numOfGroup)
            timer?.cancel()
        }
    }


    //helpers

    /**
     * checks if card index should be made active
     *
     * @param index - index to be inserted
     */
    private fun addToActive(index: Int) {
        //reduce clicks left by one
        clicksLeft.value?.let { clicks ->
            if (clicks > 0)
                clicksLeft.value = clicks - 1
        }

        // check if the last 2 active cards are different
        // if so remove all active cards
        if (actives.size > 1 &&
                cards[actives[actives.size - 2]].icon != cards[actives[actives.size - 1]].icon) {
            for (active in actives)
                cards[active] =  cards[active].copy(state = CardState.FACE_DOWN)//Card(cards[active].icon,CardState.FACE_DOWN)
            actives.clear()
        }

        // make card index active
        actives.add(index)
        cards[index] = cards[index].copy(state = CardState.FACE_UP)//Card(cards[index].icon,CardState.FACE_UP)

        // check if the last 2 active cards are different and active size is equal to group length
        // if so remove all active cards and increase the number to group solved by 1
        if (!(actives.size > 1 &&
                cards[actives[actives.size - 2]].icon != cards[actives[actives.size - 1]].icon) &&
                actives.size == mode.groupLength) {
            for (active in actives)
                cards[active] = cards[active].copy(state = CardState.SOLVED)//Card(cards[active].icon,CardState.SOLVED)
            _groupSolved.value = _groupSolved.value + 1
            actives.clear()
        }
    }

    companion object {
        const val MILLISECOND: Long = 1000L
        const val TIME_INTERVAL: Long = 10L
    }
}