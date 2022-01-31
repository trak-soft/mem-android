package com.trak.mem.scene.play.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trak.mem.R
import com.trak.mem.scene.home.component.OptionContentView
import com.trak.mem.scene.home.component.OptionImageView
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * creates a grid view based on the contents and size of icon
 *
 * @param icons - icons,
 * @param tint - tint,
 * @param backGroundColor - Color
 * @param onClick - onClick action
 * @param modifier - modifier
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridView(
    icons: List<Int>,
    tint: Color,
    backGroundColor: Color,
    onClick : (Int) -> Unit,
    modifier: Modifier = Modifier,
){
    BoxWithConstraints(modifier = modifier){
        val cw = constraints.maxWidth.toFloat()
        val ch = constraints.maxHeight.toFloat()
        val ratio = cw / ch
        val (row, column) = getRowColumn(icons.size, ratio,0.3f)
        val length = row * column

        LazyVerticalGrid(
            cells = GridCells.Fixed(row),
            modifier = Modifier
                .matchParentSize()
        ) {
            items(icons.size) {
                val w = cw / row
                OptionContentView(
                    backgroundColor = backGroundColor,
                    tint = tint,
                    modifier = Modifier
                        .fillParentMaxHeight(1f / ((length) / row))
                        .fillMaxWidth(1f / row)
                        .aspectRatio(1f, w * ((length) / row) > ch)
                        .padding(3.dp),
                    onClick = { onClick(it) },
                    onHold = {}
                ) {
                    OptionImageView(
                        icon = icons[it],
                        contentDescription = "",
                        tint = MaterialTheme.colors.onSurface,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

//@Preview(showSystemUi = true)
@Composable
fun GridViewPreview(
){
    Box(modifier = Modifier.fillMaxSize()){
        GridView(icons = mutableListOf<Int>().apply {
            for (i in 1..2 ) {
                add(R.drawable.ic_brain)
            }
        },
            tint = MaterialTheme.colors.onSurface,
            backGroundColor = MaterialTheme.colors.onSurface.copy(0.05f),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.7f)
                .align(Alignment.Center)
        )
    }
}

/**
 * gets row and column of grid based on length of array and ratio of container
 *
 * @param length - total length of grid
 * @param ratio - ratio of container w / h
 * @param range - acceptable error range
 */
fun getRowColumn(
    length: Int,
    ratio: Float,
    range: Float,
) : Pair<Int, Int> {
    var diff = Float.MAX_VALUE
    var row = 1
    var col = length
    var i = 1
    //get every combination of row col of length
    //finds the closest combination with ratio closest to ratio passed in
    while (i <= sqrt(length.toDouble())) {
        if (length % i == 0) {
            if (length / i != i) {
                col = length/i
                val new = abs((i/(col.toFloat())) - ratio)
                if (new < diff) {
                    diff = new
                    row = i
                }
            }
        }
        i++
    }
    if (diff < range)
        return row to col
    return getRowColumn(length + 1, ratio,range)
}