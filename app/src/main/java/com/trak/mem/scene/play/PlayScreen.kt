package com.trak.mem.scene.play

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun PlayScreen(
){
    Text(text = "play")
}
@Preview(showSystemUi = true)
@Composable
fun PlayScreenPreview(
){
    PlayScreen()
}