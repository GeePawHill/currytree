package org.currytree

import kotlinx.serialization.Serializable
import org.currytree.blocks.Block

@Serializable
data class PageBodyResponse(val blocks: List<Block>)
