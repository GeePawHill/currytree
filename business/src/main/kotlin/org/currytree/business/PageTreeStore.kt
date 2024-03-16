package org.currytree.business

class PageTreeRamStore(
    private val base: ValueStore<PageHeaderTree> = RamValueStore(PageHeaderTree::class.java)
) : ValueStore<PageHeaderTree> by base
