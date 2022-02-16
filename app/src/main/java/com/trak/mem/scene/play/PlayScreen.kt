package com.trak.mem.scene.play

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.trak.mem.common.model.OptionType
import com.trak.mem.common.component.OptionContentView
import com.trak.mem.common.component.GridView
import com.trak.mem.scene.play.component.HeaderView
import com.trak.mem.scene.play.model.CardState
import com.trak.mem.ui.theme.*
import kotlinx.coroutines.launch

/**
 * play screen
 */
@Destination
@Composable
fun PlayScreen(
    mode: OptionType.Mode,
) {
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
            Box(modifier = Modifier.fillMaxSize()) {
                viewModel.mode.timeLimit?.let { timeLimit ->
                    val timeLeft = viewModel.timeLeft.value
                    timeLeft?.let{
                        Box(modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .fillMaxHeight(it / timeLimit.toFloat())
                            .background(viewModel.tint.copy(0.05f))
                        )
                    }
                }

                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(start = screenPadding, end = screenPadding)
                ) {
                    Spacer(modifier = Modifier.size(screenTopSpacer))
                    HeaderView(
                        viewModel.clicksLeft.value,
                        viewModel.timeLeft.value,
                        {},
                        viewModel.tint,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(screenSecondSpacer))
                    Box(modifier = Modifier.padding(bottom = screenBottomPadding)) {
                        GridView(
                            viewModel.cards.size,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        ) { index, modifier ->
                            OptionContentView(
                                backgroundColor = viewModel.tint.copy(0.05f),
                                tint = viewModel.tint,
                                modifier = modifier,
                                onClick = {
                                    scope.launch {
                                        viewModel.onEvent(PlayScreenEvent.CardClick(index))
                                    }
                                },
                                onHold = { }
                            ) {
                                Text(
                                    when(viewModel.cards[index].state) {
                                        CardState.FACE_UP -> viewModel.cards[index].icon.toString()
                                        CardState.FACE_DOWN -> "-1"
                                        CardState.SOLVED -> viewModel.cards[index].icon.toString()
                                    },
                                    Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PlayScreenPreview(
) {
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