package org.currytree.maker

interface Connection {
    suspend fun fetchUserRoot(): PageHeader
}