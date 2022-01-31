package com.trak.mem.scene.home.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.menuOptionViewBoarderWidth
import com.trak.mem.ui.theme.menuOptionViewRoundedCorner

/**
 * Generalize Option Content view
 *
 * draws the outline of the component and takes a view as parameter
 * making menuOption more reusable
 *
 * @param backgroundColor - back ground color of composable
 * @param modifier - modifier
 * @param onClick - onclick action
 * @param onHold - onHold action
 * @param content - view to be rendered
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OptionContentView(
    backgroundColor: Color,
    tint: Color,
    modifier: Modifier,
    onClick: () -> Unit,
    onHold: () -> Unit,
    content: @Composable BoxScope.() -> Unit
){
    Surface(
        modifier = modifier.combinedClickable(
            onClick = onClick,
            onLongClick = onHold,
        ).clip(RoundedCornerShape(menuOptionViewRoundedCorner))
    ){
        Box(
            Modifier
                .border(
                    width = menuOptionViewBoarderWidth,
                    color = tint,
                    shape = RoundedCornerShape(menuOptionViewRoundedCorner)
                )
                .background(color = backgroundColor)
        ){
            content()
        }
    }
}

//@Preview(widthDp = 145, heightDp = 145)
@Composable
fun MenuOptionContentViewPreview(){
    MemandroidTheme(darkTheme = true) {
        OptionContentView(
            Color.Transparent,
            MaterialTheme.colors.onSurface,
            modifier = Modifier,
            onClick = {},
            onHold = {}
        ) {
        }
    }
}