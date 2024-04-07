package org.currytree

import org.currytree.blocks.Block
import org.currytree.business.CurryTree

@Suppress("UNCHECKED_CAST")
class DirectConnection(val business: CurryTree, val user: String) : Connection {

    private val responder = DirectResponder()

    override suspend fun childrenFor(slug: String): List<PageHeader> {
        business.childrenFor(responder, user, slug)
        return responder.lastBody as List<PageHeader>
    }

    override suspend fun bodyFor(slug: String): List<Block> {
        business.bodyFor(responder, user, slug)
        return responder.lastBody as List<Block>
    }

    fun initializeForMaker() {
        business.initializeForMaker()
    }
}
