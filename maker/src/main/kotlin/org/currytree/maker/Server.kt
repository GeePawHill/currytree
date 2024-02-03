package org.currytree.maker

class Server(val curry: CurryTree) {

    fun fetchUserRoot(): String {
        return curry.fetchUserRoot()
    }
}