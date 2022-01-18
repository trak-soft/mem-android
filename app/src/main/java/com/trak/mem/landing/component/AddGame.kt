package com.trak.mem.landing.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.R
import com.trak.mem.ui.theme.MemandroidTheme

/**
 * Add Game component
 *
 * @param modifier
 */
@Composable
fun AddGame(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.ic_add_game
) {
    Box(
        modifier = modifier
        .wrapContentSize()
    ){
        Icon(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = R.string.ic_add_game_content_description),
            tint = MaterialTheme.colors.onSurface
        )
    }
}

@Preview(name = "add game")
@Composable
fun AddGamePreview(){
    MemandroidTheme(darkTheme = true) {
        AddGame()
    }
}

@Preview("menu option add game")
@Composable
fun MenuOptionAddGamePreview(){
    MemandroidTheme(darkTheme = true) {
        MenuOption{
            AddGame(
                modifier = Modifier
                .align(Alignment.Center)
            )
        }
    }
}