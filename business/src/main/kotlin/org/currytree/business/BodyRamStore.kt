package org.currytree.business

import org.currytree.PageBody
import org.currytree.business.store.RamStore
import org.currytree.business.store.Store


class BodyRamStore(
    private val base: Store<PageBody> = RamStore(PageBody::class.java)
) : Store<PageBody> by base

