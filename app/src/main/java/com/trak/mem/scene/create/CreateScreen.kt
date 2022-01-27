package com.trak.mem.scene.create

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CreateScreen(
){
    Text(text = "create")
}
@Preview(showSystemUi = true)
@Composable
fun CreateScreenPreview(
){
    CreateScreen()
}