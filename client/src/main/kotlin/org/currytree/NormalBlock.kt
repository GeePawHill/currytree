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


interface Block {
    fun callMe(blockFactory: BlockModelFactory)
}

@Serializable
data class NormalBlock(
    val field: StyledField
) : Block {
    override fun callMe(blockFactory: BlockModelFactory) {
        blockFactory.accept(this)
    }
}
