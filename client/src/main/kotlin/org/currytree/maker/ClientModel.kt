package org.currytree.maker

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientModel(val connection: Connection) {
    val pageTree = mutableStateListOf(PageHeader.PENDING)

    fun changeWelcome() {
        pageTree.clear()
        pageTree.add(PageHeader("Hi Mom!"))
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            pageTree.clear()
            pageTree.add(connection.fetchUserRoot())
        }
    }
}