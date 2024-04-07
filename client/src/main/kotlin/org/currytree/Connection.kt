package org.currytree

import org.currytree.blocks.Block

interface Connection {
    suspend fun childrenFor(slug: String): List<PageHeader>
    suspend fun bodyFor(slug: String): List<Block>
}