package org.currytree.maker

class DirectConnection(val server: Server) : Connection {
    override suspend fun fetchUserRoot(): PageHeader {
        return server.fetchUserRoot()
    }
}
