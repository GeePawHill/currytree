package org.currytree

class DirectConnection(val server: Server) : Connection {
    override suspend fun fetchUserRoot(): PageHeader {
        return server.fetchUserRoot()
    }
}
