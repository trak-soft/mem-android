package com.trak.mem.scene.play

sealed class PlayScreenEvent{
    data class CardClick(val index: Int): PlayScreenEvent()
}
