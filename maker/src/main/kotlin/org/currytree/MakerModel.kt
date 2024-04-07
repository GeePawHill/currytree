package org.currytree

import androidx.compose.runtime.mutableStateListOf
import org.currytree.business.CurryTree

class MakerModel(val curryTree: CurryTree) {
    val clients = mutableStateListOf<ClientModel>()

    init {
        clients.add(ClientModel(DirectConnection(curryTree, "geepaw")))
        clients.add(ClientModel(DirectConnection(curryTree, "victim")))
    }
}