package com.trak.mem.scene.play.model

data class Card(
    val icon: Int,
    var state: CardState = CardState.FACE_DOWN
)

enum class CardState{
    FACE_UP,
    FACE_DOWN,
    SOLVED,
}