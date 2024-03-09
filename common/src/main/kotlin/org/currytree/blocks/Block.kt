package org.currytree.blocks

import kotlinx.serialization.Serializable

@Serializable
sealed interface Block {
    fun callMe(blockFactory: BlockModelFactory)
}