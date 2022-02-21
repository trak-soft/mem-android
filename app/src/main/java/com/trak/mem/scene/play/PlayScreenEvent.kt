package com.trak.mem.scene.play

/**
 * Play Screen Event
 *
 * @property CardClick - card click event
 * @property Reset - game state reset event
 */
sealed class PlayScreenEvent{
    data class CardClick(val index: Int): PlayScreenEvent()
    object Reset: PlayScreenEvent()
}
