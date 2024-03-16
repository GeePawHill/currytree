package org.currytree.business

import org.currytree.PageBody


class RamBodyStore(
    private val base: ValueStore<PageBody> = RamValueStore(PageBody::class.java)
) : ValueStore<PageBody> by base {

}

