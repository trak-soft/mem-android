package com.trak.mem.scene.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.R
import com.trak.mem.common.component.model.OptionType
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.optionViewPadding

/**
 * Option view
 *
 * @param options - list of optionType,
 * @param rowCount - row count,
 * @param backgroundColor - background color,
 * @param tint - border color,
 * @param onClick - on click action,
 * @param onHold - on hold action,
 * @param modifier - modifier
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OptionView(
    options : Set<OptionType>,
    rowCount: Int,
    backgroundColor: Color,
    tint: Color,
    onClick: (OptionType) -> Unit,
    onHold: (OptionType) -> Unit,
    modifier: Modifier = Modifier,
){
    LazyVerticalGrid(
        GridCells.Fixed(rowCount),
        modifier = modifier
            .fillMaxWidth(),
    ) {
        items(options.toList()){ option ->
            OptionContentView(
                backgroundColor,
                tint,
                modifier = Modifier
                    .fillMaxWidth(1f / rowCount)
                    .aspectRatio(1f)
                    .padding(optionViewPadding),
                onClick = {
                    onClick(option)
                },
                onHold = {
                    onHold(option)
                }
            ) {
                when (option) {
                    is OptionType.Mode -> {
                        OptionModeView(
                            option,
                            tint = tint
                        )
                    }
                    is OptionType.Add -> {
                        OptionImageView(
                            R.drawable.ic_add_game,
                            contentDescription = stringResource(id = R.string.ic_add_game_content_description),
                            tint = tint,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

//@Preview
@Composable
fun OptionViewPreview(){
    MemandroidTheme(darkTheme = true) {
        OptionView(
            setOf(
                OptionType.Add,
                OptionType.Mode(2,  true,9, null, null),
                OptionType.Mode(2,  true,9, null, 3),
                OptionType.Mode(2,  true,9, 67, null),
            ),
            2,
            Color.Transparent,
            MaterialTheme.colors.onSurface,
            onClick = {
            },
            onHold = {
            }
        )
    }
}