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
data class NormalBlock(val text: String, val styles: List<InlineStyleSpan> = emptyList()) {
}
