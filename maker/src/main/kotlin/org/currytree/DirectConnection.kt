package org.currytree

class DirectConnection(val server: Server) : Connection {
    override suspend fun fetchUserRoot(): PageHeader {
        return server.fetchUserRoot()
    }

    override suspend fun pagesFor(slug: String): List<PageHeader> {
        return emptyList()
    }

}
