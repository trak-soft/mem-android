package com.trak.mem.scene.landing.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.optionViewPadding

/**
 * Option type
 */
sealed class OptionType{
    data class Mode(val groupLength: Int,
                    val preview: Boolean,
                    val numOfGroup: Int,
                    val timeLimit: Int?,
                    val clickLimit: Int?) : OptionType()
    object Add: OptionType()
}

/**
 * Option view
 *
 * @param options - list of optionType
 * @param rowCount - size of row
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OptionView(
    modifier: Modifier,
    options : Set<OptionType>,
    bgColor: Color,
    rowCount: Int,
    onClick: (OptionType) -> Unit,
    onHold: (OptionType) -> Unit
){
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth(),
        cells = GridCells.Fixed(rowCount)
    ) {
        items(options.toList()){ option ->
            MenuOptionView(
                modifier = Modifier
                    .fillMaxWidth(1f / rowCount)
                    .aspectRatio(1f)
                    .padding(optionViewPadding),
                bgColor = bgColor,
                onClick = {
                    onClick(option)
                },
                onHold = {
                    onHold(option)
                }
            ) {
                when (option) {
                    is OptionType.Mode -> {
                        GameModeView(
                            modifier = Modifier,
                            groupLength = option.groupLength,
                            preview = option.preview,
                            numOfGroup = option.numOfGroup,
                            clickLimit = option.clickLimit,
                            timeLimit = option.timeLimit
                        )
                    }
                    is OptionType.Add -> {
                        AddGameView(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OptionViewPreview(){
    MemandroidTheme(darkTheme = true) {
        OptionView(
            modifier = Modifier,
            options = setOf(
                OptionType.Add,
                OptionType.Mode(2,  true,9, null, null),
                OptionType.Mode(2,  true,9, null, 3),
                OptionType.Mode(2,  true,9, 67, null),
                OptionType.Mode(2,  true,9, 23, 11),
            ),
            bgColor = Color.Transparent,
            rowCount = 2,
            onClick = {
            },
            onHold = {

            }
        )
    }
}