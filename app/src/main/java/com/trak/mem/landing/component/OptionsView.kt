package com.trak.mem.landing.component

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
import androidx.compose.ui.tooling.preview.Preview
import com.trak.mem.ui.theme.MemandroidTheme
import com.trak.mem.ui.theme.optionViewPadding

/**
 * Option type
 */
sealed class OptionType{
    data class Mode(val groupLength: Int,
                    val numOfGroup: Int,
                    val preview: Boolean,
                    val clickLimit: Int?,
                    val timeLimit: Int?) : OptionType()
    object Add: OptionType()
}

/**
 * Option view
 *
 * @param options - list of optionType
 * @param rowCount - size of row
 */
@ExperimentalFoundationApi
@Composable
fun OptionView(
    modifier: Modifier,
    options : Set<OptionType>,
    rowCount: Int = 2,
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
                onClick = {
                    when (option) {
                        is OptionType.Mode -> {}
                        is OptionType.Add -> {}
                    }
                }
            ) {
                when (option) {
                    is OptionType.Mode -> {
                        GameModeView(
                            modifier = Modifier,
                            groupLength = option.groupLength,
                            numOfGroup = option.numOfGroup,
                            preview = option.preview,
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

@ExperimentalFoundationApi
@Preview()
@Composable
fun OptionViewPreview(){
    MemandroidTheme(darkTheme = true) {
        OptionView(
            modifier = Modifier,
            options = setOf(
                OptionType.Add,
                OptionType.Add,
                OptionType.Mode(2, 9, true, null, null),
                OptionType.Mode(2, 9, true, null, 3),
                OptionType.Mode(2, 9, true, 67, null),
                OptionType.Mode(2, 9, true, 23, 11),
            )
        )
    }
}