package org.currytree

import org.currytree.blocks.Block
import org.currytree.blocks.NormalBlock
import org.currytree.business.CurryTree

@Suppress("UNCHECKED_CAST")
class DirectConnection(val business: CurryTree) : Connection {

    private val responder = DirectResponder()

    private val someBlocks = mutableListOf<NormalBlock>()

    override suspend fun fetchUserRoot(): PageHeader {
        business.fetchUserRoot(responder)
        return responder.responses.last().body as PageHeader
    }

    override suspend fun childrenFor(slug: String): List<PageHeader> {
        business.childrenFor(responder, slug)
        return responder.responses.last().body as List<PageHeader>
    }

    override suspend fun bodyFor(slug: String): List<Block> {
        business.bodyFor(responder, slug)
        return responder.responses.last().body as List<Block>
    }
}
