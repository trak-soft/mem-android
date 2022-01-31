package com.trak.mem.scene.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.trak.mem.R
import com.trak.mem.common.component.TitleView
import com.trak.mem.common.component.model.OptionType
import com.trak.mem.scene.home.component.OptionModeView
import com.trak.mem.scene.play.component.GridView
import com.trak.mem.scene.play.component.HeaderView
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.screenTopSpacer

@Destination
@Composable
fun PlayScreen(
    mode: OptionType.Mode,
){
    val viewModel = PlayViewModel(
        mode,
        tint = MaterialTheme.colors.onSurface
    )
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
            Box(modifier = Modifier.fillMaxSize()){
                println(viewModel.mode.value.timeLimit)
                println(viewModel.timeLeft.value)
                viewModel.mode.value.timeLimit?.let { timeLimit ->
                    var timeleft = viewModel.timeLeft.value
                    timeleft?.let{
                        println(timeleft / timeLimit.toFloat())
                        Box(modifier = Modifier.align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .fillMaxHeight(timeleft / timeLimit.toFloat())
                            .background(viewModel.tint.copy(0.05f))
                        )
                    }
                }

                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 30.dp, end = 30.dp, bottom = 30.dp)
                ){
                    Spacer(modifier = Modifier.size(screenTopSpacer))
                    HeaderView(
                        viewModel.mode.value.clickLimit,
                        viewModel.mode.value.timeLimit,
                        {},
                        viewModel.tint,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    Box(modifier = Modifier
                    ){
                        GridView(icons = viewModel.icons.value,
                            tint = viewModel.tint,
                            backGroundColor = viewModel.tint.copy(0.05f),
                            onClick = {},
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )
                    }
                }
            }

    }
}
@Preview(showSystemUi = true)
@Composable
fun PlayScreenPreview(
){
    MemandroidTheme(darkTheme = true) {
        PlayScreen(
            OptionType.Mode(
        2,
        true,
        9,
        null,
        4,
            )
        )
    }
}