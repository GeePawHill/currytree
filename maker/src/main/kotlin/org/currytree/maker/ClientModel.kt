package org.currytree.maker

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientModel(val connection: DirectConnection) {
    fun changeWelcome() {
        pageTree.value = "Hi Mom!"
    }

    val pageTree = mutableStateOf("...")

    init {
        CoroutineScope(Dispatchers.IO).launch {
            pageTree.value = connection.fetchUserRoot()
        }
    }
}