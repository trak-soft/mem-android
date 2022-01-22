package com.trak.mem.scene.landing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.R
import com.trak.mem.common.component.TitleView
import com.trak.mem.scene.landing.component.OptionType
import com.trak.mem.scene.landing.component.OptionView
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.landingScreenFirstSpacer
import com.trak.mem.ui.theme.landingScreenOptionViewPadding
import com.trak.mem.ui.theme.landingScreenSecondSpacer

@Composable
fun LandingScreen(
    viewModel: LandingViewModel
){
    val scaffoldState = rememberScaffoldState()
//    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Column{
            Spacer(modifier = Modifier.size(landingScreenFirstSpacer))
            TitleView(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                title = viewModel.title.value,
                image = viewModel.image.value,
                contentDescription = stringResource(id = R.string.ic_memory_content_description)
            )
            Spacer(modifier = Modifier.size(landingScreenSecondSpacer))
            OptionView(
                modifier = Modifier
                    .padding(landingScreenOptionViewPadding),
                options = viewModel.options.value,
                bgColor = MaterialTheme.colors.onSurface.copy(0.05f),
                rowCount = 2,
                onClick = { option ->
                    when (option) {
                        is OptionType.Mode -> {}
                        is OptionType.Add -> {}
                    }
                },
                onHold = { option ->
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LandingScreenPreview(

){
    MemandroidTheme(darkTheme = true) {
        LandingScreen(
            viewModel = LandingViewModel()
        )
    }
}