package org.currytree.blocks

import kotlinx.serialization.Serializable
import org.currytree.styled.StyledField


@Serializable
data class NormalBlock(
    val field: StyledField
) : Block {
    override fun callMe(blockFactory: BlockModelFactory) {
        blockFactory.accept(this)
    }
}
