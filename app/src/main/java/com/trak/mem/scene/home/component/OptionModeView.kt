package com.trak.mem.scene.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.R
import com.trak.mem.common.component.TextIconView
import com.trak.mem.common.component.model.OptionType
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.gameModePaddingViewPadding

/***
 * option mode view
 *
 * @param groupLength - length of one group
 * @param preview - if board will be preview at the start
 * @param numOfGroup - number of groups
 * @param timeLimit - time limit to solve
 *                  - view not displayed if timeLimit is null
 * @param clickLimit - max number of click
 *                   - view not displayed if clickLimit is null
 */
@Composable
fun OptionModeView(
    mode: OptionType.Mode,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onSurface,
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(all = gameModePaddingViewPadding)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextIconView(
                mode.groupLength.toString(),
                R.drawable.ic_group_length,
                stringResource(
                    R.string.ic_group_length_content_description,
                    mode.groupLength
                ),
                tint = tint
            )
            TextIconView(
                null,
                if (mode.preview) R.drawable.ic_preview else R.drawable.ic_no_preview,
                stringResource(
                    if (mode.preview)
                        R.string.ic_preview_content_description
                    else R.string.ic_no_preview_content_description
                ),
                tint = tint
            )
            TextIconView(
                mode.numOfGroup.toString(),
                R.drawable.ic_num_of_group,
                stringResource(
                    R.string.ic_group_length_content_description,
                    mode.numOfGroup
                ),
                tint = tint
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextIconView(
                mode.clickLimit.toString(),
                R.drawable.ic_click_limit,
                stringResource(
                    R.string.ic_click_limit_content_description,
                    mode.clickLimit ?: 0
                ),
                tint = tint,
                visible = mode.clickLimit != null,
            )
            TextIconView(
                mode.timeLimit.toString(),
                R.drawable.ic_time_limit,
                stringResource(
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
fun MenuOptionGameModePreview(){
    MemandroidTheme(darkTheme = true) {
        OptionContentView(
            Color.Transparent,
            MaterialTheme.colors.onSurface,
            modifier = Modifier,
            onClick = {},
            onHold = {}
        ) {
            OptionModeView(
                OptionType.Mode(
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
fun GameModeViewPreview(){
    MemandroidTheme(darkTheme = true) {
        OptionModeView(
            OptionType.Mode(
            3,
            true,
            2,
            32,
            24,
            )
        )
    }
}