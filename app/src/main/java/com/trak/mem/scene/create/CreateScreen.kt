package com.trak.mem.scene.create

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun CreateScreen(
    navigator: DestinationsNavigator
){
    Text(text = "create")
}
@Preview(showSystemUi = true)
@Composable
fun CreateScreenPreview(

){
    CreateScreen(
        EmptyDestinationsNavigator
    )
}