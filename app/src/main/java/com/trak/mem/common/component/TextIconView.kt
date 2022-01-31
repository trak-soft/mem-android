package com.trak.mem.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.trak.mem.R
import com.trak.mem.ui.theme.MemandroidTheme


/**
 * Text and image view
 *
 * @param text - text to be displayed
 *             - if text is null show no text
 * @param icon - image to be displayed
 *             - if icon is null show no icon
 * @param contentDescription - icon content description
 * @param modifier - modifier
 * @param fontSize - fontSize
 * @param fontWeight - font weight
 * @param letterSpacing - spacing between letter
 * @param tint - Color of icon and image
 * @param iconSize - width and height of image
 *                  - if image size is null image size is default image size
 * @param space - space between text and image
 * @param visible -  is view visible
 */
@Composable
fun TextIconView(
    text: String?,
    icon: Int?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit? = null,
    fontWeight: FontWeight? = null,
    letterSpacing: TextUnit? = null,
    tint: Color? = null,
    style: TextStyle = LocalTextStyle.current,
    iconSize: Dp? = null,
    space: Dp = 0.dp,
    visible: Boolean = true,
) {
    Row(
        modifier = modifier
            .wrapContentSize()
    ) {
        if (visible) {
            text?.let { text ->
                Text(
                    text,
                    style = style.merge(
                        TextStyle(
                            color = tint ?: style.color,
                            fontSize = fontSize ?: style.fontSize ,
                            fontWeight = fontWeight ?: style.fontWeight,
                            letterSpacing = letterSpacing ?: style.letterSpacing,
                        )
                    ),
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
            Spacer(modifier = Modifier.size(width = space, height = 0.dp))
            icon?.let {
                var iconModifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                iconSize?.let { iconModifier = iconModifier.size(it) }
                Icon(
                    painterResource(id = icon),
                    contentDescription,
                    tint =tint ?: MaterialTheme.colors.onSurface,
                    modifier = iconModifier,
                )
            }
        }
    }
}

//@Preview("group length")
@Composable
fun InfoViewPreview(){
    MemandroidTheme(darkTheme = true) {
        Column {
            TextIconView(
                "12",
                R.drawable.ic_group_length,
                "",
            )

            Spacer(modifier = Modifier.height(10.dp))
            TextIconView(
                null,
                R.drawable.ic_preview,
                "",
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextIconView(
                null,
                R.drawable.ic_no_preview,
                "",
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextIconView(
                "42",
                R.drawable.ic_num_of_group,
                "",
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextIconView(
                "2",
                R.drawable.ic_time_limit,
                "",
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextIconView(
                "2",
                R.drawable.ic_click_limit,
                "",
            )
        }
    }
}