package org.currytree.business

import org.currytree.PageHeader

class CurryTree {

    val pageHeaderTree = PageHeaderTree()

    fun fetchUserRoot(): PageHeader {
        return PageHeader("Welcome!")
    }

    fun childrenFor(slug: String): List<PageHeader> {
        return pageHeaderTree.childrenFor(slug)
    }
}