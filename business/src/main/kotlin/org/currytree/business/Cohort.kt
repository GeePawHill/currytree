package org.currytree.business

import kotlinx.serialization.Serializable
import org.currytree.PageBody
import org.currytree.PageBodyResponse
import org.currytree.business.store.Store
import org.currytree.io.Responder
import java.nio.file.Path

@Serializable
data class Cohort(val pages: PageHeaderTree = PageHeaderTree()) {
    fun bodyFor(responder: Responder, bodies: Store<PageBody>, user: User, slug: String) {
        val body = bodies.read(Path.of(slug))
        responder.ok(PageBodyResponse(body.blocks))
    }
}