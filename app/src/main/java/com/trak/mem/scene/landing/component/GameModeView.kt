package com.trak.mem.scene.landing.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trak.mem.R
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.gameModePaddingViewPadding
import com.trak.mem.ui.theme.menuOptionTextStyle


/**
 * info view
 *
 * @param number - number of info
 * @param image - info image
 * @param contentDescription - icon content description
 */
@Composable
fun InfoView(
    modifier : Modifier,
    number: Int?,
    image: Int,
    contentDescription: String,
    visible : Boolean,
){
    Row(
        modifier = modifier
            .wrapContentSize()
    ) {
        if (visible) {
            number?.let {
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    text = it.toString(),
                    color = MaterialTheme.colors.onSurface,
                    style = menuOptionTextStyle
                )
            }
            Icon(
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                painter = painterResource(id = image),
                contentDescription = contentDescription,
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

/***
 * game mode view
 *
 * @param groupLength - length of one group
 * @param numOfGroup - number of groups
 * @param preview - if board will be preview at the start
 * @param clickLimit - max number of click
 * @param timeLimit - time limit to solve
 */
@Composable
fun GameModeView(
    modifier: Modifier,
    groupLength: Int,
    preview: Boolean,
    numOfGroup: Int,
    timeLimit: Int? = null,
    clickLimit: Int? = null,
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
            InfoView(
                modifier = Modifier,
                number = groupLength,
                image = R.drawable.ic_group_length,
                contentDescription = stringResource(
                    id = R.string.ic_group_length_content_description,
                    groupLength
                ),
                visible = true
            )
            InfoView(
                modifier = Modifier,
                number = null,
                image = if (preview) R.drawable.ic_preview else R.drawable.ic_no_preview,
                contentDescription = stringResource(
                    id = if (preview)
                        R.string.ic_preview_content_description
                    else R.string.ic_no_preview_content_description
                ),
                visible = true
            )
            InfoView(
                modifier = Modifier,
                number = numOfGroup,
                image = R.drawable.ic_num_of_group,
                contentDescription = stringResource(
                    id = R.string.ic_group_length_content_description,
                    numOfGroup
                ),
                visible = true
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            InfoView(
                modifier = Modifier,
                number = timeLimit,
                image = R.drawable.ic_time_limit,
                contentDescription = stringResource(
                    id = R.string.ic_time_limit_content_description,
                    timeLimit ?: 0
                ),
                visible = timeLimit != null
            )
            InfoView(
                modifier = Modifier,
                number = clickLimit,
                image = R.drawable.ic_click_limit,
                contentDescription = stringResource(
                    id = R.string.ic_click_limit_content_description,
                    clickLimit ?: 0
                ),
                visible = clickLimit != null
            )
        }
    }
}

@Preview("group length")
@Composable
fun GamModeWidgetViewPreview(){
    MemandroidTheme(darkTheme = true) {
        Column {
            InfoView(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                number = 2,
                image = R.drawable.ic_group_length,
                contentDescription = "",
                visible = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            InfoView(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                number = null,
                image = R.drawable.ic_preview,
                contentDescription = "",
                visible = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            InfoView(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                number = null,
                image = R.drawable.ic_no_preview,
                contentDescription = "",
                visible = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            InfoView(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                number = 2,
                image = R.drawable.ic_num_of_group,
                contentDescription = "",
                visible = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            InfoView(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                number = 2,
                image = R.drawable.ic_time_limit,
                contentDescription = "",
                visible = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            InfoView(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                number = 2,
                image = R.drawable.ic_click_limit,
                contentDescription = "",
                visible = true
            )
        }
    }
}

@Preview(name = "game mode menu option", widthDp = 145, heightDp = 145)
@Composable
fun MenuOptionGameModePreview(){
    MemandroidTheme(darkTheme = true) {
        MenuOptionView(
            modifier = Modifier,
            bgColor = Color.Transparent,
            onClick = {},
            onHold = {}
        ) {
            GameModeView(
                modifier = Modifier,
                groupLength = 3,
                preview = true,
                numOfGroup = 2,
                timeLimit = null,
                clickLimit = 4
            )
        }
    }
}

@Preview(name = "menu option game mode", widthDp = 145, heightDp = 145)
@Composable
fun GameModeViewPreview(){
    MemandroidTheme(darkTheme = true) {
        GameModeView(
            modifier = Modifier,
            groupLength = 3,
            preview = true,
            numOfGroup = 2,
            timeLimit = 24,
            clickLimit = 56
        )
    }
}