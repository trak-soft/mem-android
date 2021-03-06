package com.trak.mem.common.component

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.trak.mem.R
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.titleViewImageSize
import com.trak.mem.ui.theme.titleViewSpaceBetween

/**
 * title view
 *
 * @param title - title text
 * @param icon - icon resource
 * @param contentDescription - image content description
 * @param tint - tint color
 * @param modifier - modifier
 */
@Composable
fun TitleView(
    title: String,
    icon: Int,
    contentDescription: String,
    tint: Color,
    modifier: Modifier = Modifier,
) {
    TextIconView(
        text = title.uppercase(),
        icon = icon,
        contentDescription = contentDescription,
        tint = tint,
        style = MaterialTheme.typography.h4,
        iconSize = titleViewImageSize,
        space = titleViewSpaceBetween,
        modifier = modifier.wrapContentSize(),
    )
}

//@Preview
@Composable
fun TitleViewPreview() {
    MemandroidTheme(darkTheme = true) {
        TitleView(
            title = stringResource(id = R.string.memory),
            icon = R.drawable.ic_brain,
            contentDescription = stringResource(id = R.string.ic_memory_content_description),
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier,
        )
    }

}