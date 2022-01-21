package com.trak.mem.landing.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
@Composable
fun MenuOptionView(
    modifier: Modifier,
    bgColor: Color = Color.Gray,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(menuOptionViewRoundedCorner))
            .border(
                width = menuOptionViewBoarderWidth,
                color = MaterialTheme.colors.onSurface,
                shape = RoundedCornerShape(menuOptionViewRoundedCorner)
            )
            .background(color = bgColor)
            .clickable { onClick() }
    ){
        content()
    }
}

@Preview(widthDp = 145, heightDp = 145)
@Composable
fun MenuOptionViewPreview(){
    MemandroidTheme(darkTheme = true) {
        MenuOptionView(modifier = Modifier) {
        }
    }
}