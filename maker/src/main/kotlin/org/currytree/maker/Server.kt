package org.currytree.maker

class Server(val curry: CurryTree) {

    fun fetchUserRoot(): PageHeader {
        return curry.fetchUserRoot()
    }
}