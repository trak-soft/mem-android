package com.trak.mem.scene.play

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.trak.mem.R
import com.trak.mem.common.component.TitleView
import com.trak.mem.ui.theme.screenTopSpacer

@Destination
@Composable
fun PlayScreen(
){
    val viewModel = PlayViewModel()
    val scaffoldState = rememberScaffoldState()
//    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()){
            Spacer(modifier = Modifier.size(screenTopSpacer))
            TitleView(
                "play",
                R.drawable.ic_time_limit,
                stringResource(id = R.string.ic_add_game_content_description),
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.fillMaxSize())
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PlayScreenPreview(
){
    PlayScreen()
}