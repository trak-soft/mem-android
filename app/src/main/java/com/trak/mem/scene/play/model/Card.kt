package com.trak.mem.scene.play.model

data class Card(
    val icon: Int,
    var iconState: CardState = CardState.FACE_DOWN
)
