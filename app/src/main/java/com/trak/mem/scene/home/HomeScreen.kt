package com.trak.mem.scene.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.trak.mem.R
import com.trak.mem.common.component.TitleView
import com.trak.mem.common.model.OptionType
import com.trak.mem.scene.destinations.EditScreenDestination
import com.trak.mem.scene.destinations.PlayScreenDestination
import com.trak.mem.common.component.OptionContentView
import com.trak.mem.common.component.OptionImageView
import com.trak.mem.scene.home.component.OptionModeView
import com.trak.mem.common.component.GridView
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.screenPadding
import com.trak.mem.ui.theme.screenSecondSpacer
import com.trak.mem.ui.theme.screenTopSpacer

/**
 * home screen
 */
@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val viewModel = HomeViewModel()
    val tint = MaterialTheme.colors.onSurface
    val optionColor = MaterialTheme.colors.onSurface.copy(0.05f)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier
                .fillMaxSize()
                .padding(start = screenPadding, end = screenPadding)
        ) {
            Spacer(modifier = Modifier.size(screenTopSpacer))
            //title
            TitleView(
                title = viewModel.title,
                icon = viewModel.icon,
                contentDescription = stringResource(id = R.string.ic_memory_content_description),
                tint = tint,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.size(screenSecondSpacer))
            //game mode
            Box(modifier = Modifier) {
                GridView(viewModel.options.value.size,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    padding = 10.dp,
                    inf = true,
                    rowCount = 2
                ) { index, modifier->
                    val option = viewModel.options.value.toList()[index]
                    OptionContentView(
                        backgroundColor = optionColor,
                        tint = tint,
                        modifier = modifier,
                        onClick = {
                            when (option) {
                                is OptionType.MODE -> {
                                    navigator.navigate(
                                        PlayScreenDestination(mode = option)
                                    )
                                }
                                is OptionType.ADD -> {
                                    navigator.navigate(
                                        EditScreenDestination
                                    )
                                }
                            }
                        },
                        onHold = {
                            navigator.navigate(
                                EditScreenDestination
                            )
                        }
                    ) {
                        when (option) {
                            is OptionType.MODE -> {
                                OptionModeView(
                                    mode = option,
                                    tint = tint
                                )
                            }
                            is OptionType.ADD -> {
                                OptionImageView(
                                    icon = R.drawable.ic_add_game,
                                    contentDescription = stringResource(id = R.string.ic_add_game_content_description),
                                    tint = tint,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    MemandroidTheme(darkTheme = false) {
        HomeScreen(
            EmptyDestinationsNavigator
        )
    }
}