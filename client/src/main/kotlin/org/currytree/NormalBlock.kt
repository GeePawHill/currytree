package org.currytree

import kotlinx.serialization.Serializable

enum class InlineStyle {
    italic,
    underline,
    bold,
    code,
}

@Serializable
data class InlineStyleSpan(val start: Int, val end: Int, val style: InlineStyle)

@Serializable
data class StyledField(val text: String, val spans: List<InlineStyleSpan> = emptyList())

@Serializable
data class NormalBlock(
    val field: StyledField
) {
    val text = field.text
    val spans = field.spans
}
