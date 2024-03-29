package org.currytree

import org.currytree.blocks.Block
import org.currytree.business.CurryTree

@Suppress("UNCHECKED_CAST")
class DirectConnection(val business: CurryTree) : Connection {

    private val responder = DirectResponder()

    override suspend fun fetchUserRoot(): PageHeader {
        business.fetchUserRoot(responder)
        return responder.lastBody as PageHeader
    }

    override suspend fun childrenFor(slug: String): List<PageHeader> {
        business.childrenFor(responder, slug)
        return responder.lastBody as List<PageHeader>
    }

    override suspend fun bodyFor(slug: String): List<Block> {
        business.bodyFor(responder, slug)
        return responder.lastBody as List<Block>
    }

    fun initializeForMaker() {
        business.initializeForMaker()
    }
}
