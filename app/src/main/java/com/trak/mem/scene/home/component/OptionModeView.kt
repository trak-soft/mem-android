package com.trak.mem.scene.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.trak.mem.R
import com.trak.mem.common.component.OptionContentView
import com.trak.mem.common.component.TextIconView
import com.trak.mem.common.model.OptionType
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.gameModePaddingViewPadding

/***
 * option mode view
 *
 * @param mode - mode setting
 * @param modifier - modifier
 * @param tint - tint color
 */
@Composable
fun OptionModeView(
    mode: OptionType.MODE,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onSurface,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(all = gameModePaddingViewPadding)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextIconView(
                text = mode.groupLength.toString(),
                icon = R.drawable.ic_group_length,
                contentDescription = stringResource(
                    R.string.ic_group_length_content_description,
                    mode.groupLength
                ),
                tint = tint
            )
            TextIconView(
                text = null,
                icon = if (mode.preview) R.drawable.ic_preview else R.drawable.ic_no_preview,
                contentDescription = stringResource(
                    if (mode.preview)
                        R.string.ic_preview_content_description
                    else R.string.ic_no_preview_content_description
                ),
                tint = tint
            )
            TextIconView(
                text = mode.numOfGroup.toString(),
                icon = R.drawable.ic_num_of_group,
                contentDescription = stringResource(
                    R.string.ic_group_length_content_description,
                    mode.numOfGroup
                ),
                tint = tint
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextIconView(
                text = mode.clickLimit.toString(),
                icon = R.drawable.ic_click_limit,
                contentDescription = stringResource(
                    R.string.ic_click_limit_content_description,
                    mode.clickLimit ?: 0
                ),
                tint = tint,
                visible = mode.clickLimit != null,
            )
            TextIconView(
                text = mode.timeLimit.toString(),
                icon = R.drawable.ic_time_limit,
                contentDescription = stringResource(
                    R.string.ic_time_limit_content_description,
                    mode.timeLimit ?: 0
                ),
                tint = tint,
                visible = mode.timeLimit != null,
            )
        }
    }
}

//@Preview(name = "game mode menu option", widthDp = 145, heightDp = 145)
@Composable
fun MenuOptionGameModePreview() {
    MemandroidTheme(darkTheme = true) {
        OptionContentView(
            Color.Transparent,
            MaterialTheme.colors.onSurface,
            modifier = Modifier,
            onClick = {},
            onHold = {}
        ) {
            OptionModeView(
                OptionType.MODE(
                3,
                true,
                2,
                null,
                4,
                )
            )
        }
    }
}

//@Preview(name = "menu option game mode", widthDp = 145, heightDp = 145)
@Composable
fun GameModeViewPreview() {
    MemandroidTheme(darkTheme = true) {
        OptionModeView(
            OptionType.MODE(
            3,
            true,
            2,
            32,
            24,
            )
        )
    }
}