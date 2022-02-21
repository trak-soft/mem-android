package com.trak.mem.scene.play.model

/**
 * different game state
 *
 * @property INIT - initial game state
 * @property PREVIEW - preview cards before playing
 * @property PLAY - currently playing the game
 * @property OVER - game is over
 */
sealed class GameState {
    object INIT : GameState()
    object PREVIEW: GameState()
    object PLAY: GameState()
     //@property won - checks if user won or lose
    data class OVER(val won: Boolean) : GameState()
}