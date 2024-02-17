package org.currytree

interface Connection {
    suspend fun fetchUserRoot(): PageHeader
    suspend fun pagesFor(slug: String): List<PageHeader>
}