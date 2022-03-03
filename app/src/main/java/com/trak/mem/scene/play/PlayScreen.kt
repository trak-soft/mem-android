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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.trak.mem.common.model.OptionType
import com.trak.mem.common.component.OptionContentView
import com.trak.mem.common.component.GridView
import com.trak.mem.scene.play.component.HeaderView
import com.trak.mem.scene.play.model.CardState
import com.trak.mem.ui.theme.*
import kotlinx.coroutines.launch
import kotlin.math.ceil

/**
 * play screen
 *
 * @param mode - game mode being played
 */
@Destination
@Composable
fun PlayScreen(
    mode: OptionType.MODE,
) {
    val viewModel = PlayViewModel(
        mode = mode
    )
    val tint: Color = MaterialTheme.colors.onSurface
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val cardTint : (Int) -> Color = { index ->
        when(viewModel.cards[index].state) {
            CardState.FACE_UP -> faceUpColor
            CardState.FACE_DOWN -> tint.copy(0.05f)
            CardState.SOLVED -> solvedColor
            CardState.WRONG -> wrongColor
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            //time limit view tracker
            viewModel.mode.timeLimit?.let { timeLimit ->
                val timeLeft = viewModel.timeLeft.value
                timeLeft?.let{
                    Box(modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .fillMaxHeight(it / (timeLimit * 1000L).toFloat())
                        .background(tint.copy(0.05f))
                    )
                }
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(start = screenPadding, end = screenPadding)
            ) {
                Spacer(modifier = Modifier.size(screenTopSpacer))
                //header view
                HeaderView(
                    clickLimit = viewModel.clicksLeft.value,
                    timeLimit = viewModel.timeLeft.value?.let { ceil(it / 1000L.toFloat()).toLong() },
                    { scope.launch {
                        viewModel.onEvent(PlayScreenEvent.Reset)
                    } },
                    tint = tint,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.size(screenSecondSpacer))
                //grid view
                Box(modifier = Modifier.padding(bottom = screenBottomPadding)) {
                    GridView(
                        size = viewModel.cards.size,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    ) { index, modifier ->
                        OptionContentView(
                            backgroundColor = cardTint(index),
                            tint = tint,
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
                                    CardState.FACE_DOWN -> "-1"
                                    else -> viewModel.cards[index].icon.toString()
                                },
                                modifier = Modifier.align(Alignment.Center),
                                color = tint
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
            OptionType.MODE(
        2,
        true,
        9,
        null,
        4,
            )
        )
    }
}