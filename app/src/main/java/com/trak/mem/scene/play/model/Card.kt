package com.trak.mem.scene.play.model

/**
 * Card model class
 *
 * @property icon  - card icon
 * @property state  - card state
 */
data class Card(
    val icon: Int,
    var state: CardState = CardState.FACE_DOWN
)