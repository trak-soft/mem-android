package com.trak.mem.landing.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.trak.mem.ui.theme.MemandroidTheme

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
 * @param padding - element padding
 */
@ExperimentalFoundationApi
@Composable
fun OptionView(
    modifier: Modifier,
    options : List<OptionType>,
    rowCount: Int = 2,
    padding: Dp = 15.dp
){
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth(),
        cells = GridCells.Fixed(rowCount)
    ) {
        items(options.size) {
            val option = options[it]
            MenuOptionView(
                modifier = Modifier
                    .fillMaxWidth(1f / rowCount)
                    .aspectRatio(1f)
                    .padding(padding),
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
            options = listOf(
                OptionType.Add,
                OptionType.Mode(2, 9, true, null, null),
                OptionType.Add,
                OptionType.Add,
                OptionType.Mode(2, 9, true, null, null),
                OptionType.Mode(2, 9, true, 23, 11),
                OptionType.Add,
            )
        )
    }

}