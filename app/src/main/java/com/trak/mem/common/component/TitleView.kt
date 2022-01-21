package com.trak.mem.common.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.R
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.titleViewSpaceBetween

/**
 * title view
 *
 * @param title - title text
 * @param image - image resource
 * @param contentDescription - image content description
 */
@Composable
fun TitleView(
    modifier: Modifier,
    title: String,
    image: Int,
    contentDescription: String
){
    Row(modifier = modifier.wrapContentSize()) {
        Text(
            title.uppercase(),
            modifier = Modifier.align(Alignment.CenterVertically),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.h4,
        )
        Spacer(modifier = Modifier.size(titleViewSpaceBetween))
        Icon(
            painter = painterResource(id = image),
            contentDescription = contentDescription,
            modifier = Modifier.align(Alignment.CenterVertically),
            tint = MaterialTheme.colors.onSurface,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TitleViewPreview(){
    MemandroidTheme(darkTheme = false) {
        TitleView(
            modifier = Modifier,
            title = stringResource(id = R.string.memory),
            image = R.drawable.ic_brain,
            contentDescription = stringResource(id = R.string.ic_memory_content_description)
        )
    }

}