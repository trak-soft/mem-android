package com.trak.mem.scene.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.trak.mem.R
import com.trak.mem.common.component.TitleView
import com.trak.mem.scene.destinations.EditScreenDestination
import com.trak.mem.scene.destinations.PlayScreenDestination
import com.trak.mem.scene.home.component.OptionView
import com.trak.mem.common.component.model.OptionType
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.landingScreenOptionViewPadding
import com.trak.mem.ui.theme.landingScreenSecondSpacer
import com.trak.mem.ui.theme.screenTopSpacer

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
){
    val viewModel = HomeViewModel(
        tint = MaterialTheme.colors.onSurface,
        optionColor = MaterialTheme.colors.onSurface.copy(0.05f)
    )
    val scaffoldState = rememberScaffoldState()
//    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()){
            Spacer(modifier = Modifier.size(screenTopSpacer))
            TitleView(
                viewModel.title,
                viewModel.icon,
                stringResource(id = R.string.ic_memory_content_description),
                tint = viewModel.tint,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.size(landingScreenSecondSpacer))
            OptionView(
                viewModel.options.value,
                2,
                viewModel.optionColor,
                tint = viewModel.tint,
                onClick = { option ->
                    when (option) {
                        is OptionType.Mode -> {
                            navigator.navigate(
                                PlayScreenDestination(mode = option)
                            )
                        }
                        is OptionType.Add -> {
                            navigator.navigate(
                                EditScreenDestination
                            )
                        }
                    }
                },
                onHold = {
                },
                modifier = Modifier
                    .padding(
                    start = landingScreenOptionViewPadding,
                    top = landingScreenOptionViewPadding,
                    end = landingScreenOptionViewPadding
                ),
            )
        }
    }
}

//@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    MemandroidTheme(darkTheme = false) {
        HomeScreen(
            EmptyDestinationsNavigator
        )
    }
}