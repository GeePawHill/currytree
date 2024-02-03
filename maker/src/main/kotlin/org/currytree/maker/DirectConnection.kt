package org.currytree.maker

class DirectConnection(val server: Server) {
    suspend fun fetchUserRoot(): String {
        return server.fetchUserRoot()
    }
}
