package org.currytree

import kotlinx.serialization.Serializable
import org.currytree.blocks.Block

@Serializable
data class PageBody(val slug: String, val blocks: List<Block>)