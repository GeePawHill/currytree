package org.currytree

interface Connection {
    suspend fun fetchUserRoot(): PageHeader
    suspend fun childrenFor(slug: String): List<PageHeader>
    suspend fun bodyFor(slug: String): List<NormalBlock>
}