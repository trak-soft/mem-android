package com.trak.mem.scene.play

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun PlayScreen(
    navigator: DestinationsNavigator
){
    Text(text = "play")
}
@Preview(showSystemUi = true)
@Composable
fun PlayScreenPreview(

){
    PlayScreen(
        EmptyDestinationsNavigator
    )
}