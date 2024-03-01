package org.currytree

import kotlinx.serialization.Serializable

@Serializable
data class CodeBlock(
    val field: StyledField
) : Block {
}
