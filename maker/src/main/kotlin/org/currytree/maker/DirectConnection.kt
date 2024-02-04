package org.currytree.maker

class DirectConnection(val server: Server) {
    suspend fun fetchUserRoot(): PageHeader {
        return server.fetchUserRoot()
    }
}
