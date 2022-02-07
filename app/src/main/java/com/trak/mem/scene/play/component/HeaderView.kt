package com.trak.mem.scene.play.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trak.mem.R
import com.trak.mem.common.component.TextIconView

/**
 * Header View
 *
 * @param clickLimit - click limit when null no limit
 * @param timeLimit - time limit when null no limit
 * @param onReset - on reset action
 * @param tint - tint color
 * @param modifier -modifier
 */
@Composable
fun HeaderView(
    clickLimit: Int?,
    timeLimit: Long?,
    onReset: () -> Unit,
    tint: Color,
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        TextIconView(
            (clickLimit ?: "  ").toString(),
            R.drawable.ic_click_limit,
            stringResource(id = R.string.ic_click_limit_content_description),
            fontSize = 32.sp,
            iconSize = 32.dp,
            tint = tint,
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_reset),
            contentDescription = stringResource(id = R.string.ic_add_game_content_description),
            tint = tint,
            modifier = Modifier.clickable {
                onReset()
            }
        )
        TextIconView(
            (timeLimit ?: "  ").toString(),
            R.drawable.ic_time_limit,
            stringResource(id = R.string.ic_time_limit_content_description),
            fontSize = 32.sp,
            iconSize = 32.dp,
            tint = tint,
        )
    }
}

//@Preview()
@Composable
fun HeaderViewPreview(){
        HeaderView(
            23,
            33,
            {},
            MaterialTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth()
        )
}