package com.trak.mem.landing.component

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.menuOptionViewBoarderWidth
import com.trak.mem.ui.theme.menuOptionViewRoundedCorner

/**
 * Generalize MenuOption Composable Component
 *
 * draws the outline of the composable and take in a Composable content
 * making menuOption more reusable
 *
 * @param modifier
 * @param bgColor back ground color of composable
 * @param content child composable
 */
@ExperimentalFoundationApi
@Composable
fun MenuOptionView(
    modifier: Modifier,
    bgColor: Color,
    onClick: () -> Unit,
    onHold: () -> Unit,
    content: @Composable BoxScope.() -> Unit
){
    Surface(
        modifier = modifier.combinedClickable(
            onClick = onClick,
            onLongClick = onHold,
        )
    ){
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(menuOptionViewRoundedCorner))
                .border(
                    width = menuOptionViewBoarderWidth,
                    color = MaterialTheme.colors.onSurface,
                    shape = RoundedCornerShape(menuOptionViewRoundedCorner)
                )
                .background(color = bgColor)
        ){
            content()
        }
    }
}

@ExperimentalFoundationApi
@Preview(widthDp = 145, heightDp = 145)
@Composable
fun MenuOptionViewPreview(){
    MemandroidTheme(darkTheme = true) {
        MenuOptionView(
            modifier = Modifier,
            bgColor = Color.Transparent,
            onClick = {},
            onHold = {}
        ) {
        }
    }
}