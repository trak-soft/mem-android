package com.trak.mem.scene.home.component

import android.accounts.AuthenticatorDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.R
import com.trak.mem.ui.theme.MemandroidTheme

/**
 * Add Game component
 *
 * @param icon - icon resource
 * @param contentDescription - icon content description
 * @param tint - icon tint color
 * @param modifier - modifier
 */
@Composable
fun  OptionImageView(
    icon: Int,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .wrapContentSize()
    ){
        Icon(
            painterResource(id = icon),
            contentDescription,
            tint = tint
        )
    }
}

//@Preview(name = "add game")
@Composable
fun OptionImageViewPreview(){
    MemandroidTheme(darkTheme = true) {
        OptionImageView(
            R.drawable.ic_add_game,
            contentDescription = stringResource(id = R.string.ic_add_game_content_description),
            tint = MaterialTheme.colors.onSurface
        )
    }
}

@ExperimentalFoundationApi
//@Preview("menu option add game", widthDp = 145, heightDp = 145)
@Composable
fun MenuOptionViewOptionImagePreview(){
    MemandroidTheme(darkTheme = true) {
        OptionContentView(
            Color.Transparent,
            MaterialTheme.colors.onSurface,
            modifier = Modifier,
            onClick = {},
            onHold = {}
        ){
            OptionImageView(
                R.drawable.ic_add_game,
                contentDescription = stringResource(id = R.string.ic_add_game_content_description),
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}