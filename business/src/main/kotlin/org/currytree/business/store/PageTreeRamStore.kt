package org.currytree.business.store

import org.currytree.business.PageHeaderTree

class PageTreeRamStore(
    private val base: Store<PageHeaderTree> = RamStore(PageHeaderTree::class.java)
) : Store<PageHeaderTree> by base
