package com.trak.mem.scene.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.trak.mem.R
import com.trak.mem.common.component.TitleView
import com.trak.mem.ui.theme.screenTopSpacer

/**
 * Edit Screen
 */
@Destination
@Composable
fun EditScreen(
) {
    val viewModel = EditViewModel()
    val tint = MaterialTheme.colors.onSurface
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.size(screenTopSpacer))
            //title
            TitleView(
                title = viewModel.title,
                icon = viewModel.icon,
                contentDescription = stringResource(id = R.string.ic_add_game_content_description),
                tint = tint,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}
//@Preview(showSystemUi = true)
@Composable
fun EditScreenPreview(
) {
    EditScreen()
}