package org.currytree.maker

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientModel(val connection: Connection) {
    val pageTree = mutableStateOf(PageHeader.PENDING)

    fun changeWelcome() {
        pageTree.value = PageHeader("Hi Mom!")
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            pageTree.value = connection.fetchUserRoot()
        }
    }
}