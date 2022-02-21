package com.trak.mem.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Option type
 */
sealed class OptionType{
    @Parcelize
    data class MODE(val groupLength: Int,
                    val preview: Boolean,
                    val numOfGroup: Int,
                    val clickLimit: Int?,
                    val timeLimit: Int?) : OptionType(), Parcelable

    object ADD: OptionType()
}