package org.currytree.maker

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientModel(val connection: Connection) {
    val pageTree = mutableStateListOf(PageHeader.PENDING)

    fun changeWelcome() {
        pageTree.clear()
        pageTree.add(PageHeader("Hi Mom!", true))
        pageTree.add(PageHeader("Oh, and Dad, too!", false, 1))
    }

    fun expanded(pageHeader: PageHeader, nowExpanded: Boolean) {
        println("Expanded -> ${pageHeader.title} = $nowExpanded")
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            pageTree.clear()
            pageTree.add(connection.fetchUserRoot())
        }
    }
}