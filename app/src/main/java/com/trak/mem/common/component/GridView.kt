package com.trak.mem.common.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.sqrt

/**
 * creates a grid view based on the contents and size of icon
 *
 * @param size - size of list
 * @param modifier - modifier
 * @param content - of grid
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridView(
    size: Int,
    modifier: Modifier = Modifier,
    padding: Dp = 3.dp,
    inf: Boolean = false,
    rowCount: Int? = null,
    content: @Composable BoxScope.(index: Int, modifier: Modifier) -> Unit,
){
    BoxWithConstraints(modifier = modifier){
        val cw = constraints.maxWidth.toFloat()
        val ch = constraints.maxHeight.toFloat()
        val ratio = cw / ch
        var (row, column) = getRowColumn(size, ratio,0.3f)
        println(listOf(row,column)+"asdsads")
        println(listOf(cw,ch))
        row  = (rowCount ?: row)
        rowCount?.let {
            column = ceil(size / row.toFloat()).toInt()
        }

        LazyVerticalGrid(
            cells = GridCells.Fixed(row),
            modifier = Modifier
                .matchParentSize()
        ) {
            items(size) {
                var mod =  modifier
                if (!inf){
                    mod = mod.fillParentMaxHeight(1f / column)
                }
                val w = cw / row
                content(
                    it,
                    modifier = mod
                        .fillMaxWidth(1f / row)
                        .aspectRatio(1f, (w * column > ch) && !inf)
                        .padding(padding),
                )
            }
        }
    }
}

//@Preview
@Composable
fun GridViewPreview(
){
    Box(modifier = Modifier.fillMaxSize()){
        GridView(
            4
        ){ index, modifier ->
            Text(
                index.toString(),
                modifier = modifier.border(3.dp,Color.Red, RoundedCornerShape(3.dp))
            )
        }
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
                val tcol = length/i
                print("ratio:$ratio - ${i/tcol.toFloat()} all: ${listOf(i,tcol)}")
                val new = abs((i/(tcol.toFloat())) - ratio)
                if (new < diff) {
                    diff = new
                    row = i
                    col = tcol
                }
            }
        }
        i++
    }
    if (diff < range)
        return row to col
    return getRowColumn(length + 1, ratio,range)
}