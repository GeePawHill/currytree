package org.currytree.business

import org.currytree.business.store.RamStore
import org.currytree.business.store.Store

class PageTreeRamStore(
    private val base: Store<PageHeaderTree> = RamStore(PageHeaderTree::class.java)
) : Store<PageHeaderTree> by base
