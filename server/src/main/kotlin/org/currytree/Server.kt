package org.currytree

import org.currytree.business.CurryTree

class Server(val curry: CurryTree) {

    fun fetchUserRoot(): PageHeader {
        return curry.fetchUserRoot()
    }

    fun childrenFor(slug: String): List<PageHeader> {
        return curry.childrenFor(slug)
    }
}