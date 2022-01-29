package com.trak.mem.common.component.model

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