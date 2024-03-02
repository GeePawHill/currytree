package org.currytree

import org.currytree.blocks.Block
import org.currytree.blocks.NormalBlock
import org.currytree.business.CurryTree

class DirectConnection(val business: CurryTree) : Connection {

    private val someBlocks = mutableListOf<NormalBlock>()

    override suspend fun fetchUserRoot(): PageHeader {
        return business.fetchUserRoot()
    }

    override suspend fun childrenFor(slug: String): List<PageHeader> {
        return business.childrenFor(slug)
    }

    override suspend fun bodyFor(slug: String): List<Block> {
        return business.bodyFor(slug)
    }
}
