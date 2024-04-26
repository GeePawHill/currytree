package org.currytree.business.store

import org.currytree.PageBody


class BodyRamStore(
    private val base: Store<PageBody> = RamStore(PageBody::class.java)
) : Store<PageBody> by base

