package org.currytree

interface Connection {
    suspend fun fetchUserRoot(): PageHeader
}