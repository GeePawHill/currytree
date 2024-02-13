package org.currytree

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClientModel(val connection: Connection) {
    val pageTree = mutableStateListOf(PageHeader.PENDING)
    val uiTree = UiTreeNode("root")

    fun changeWelcome() {
        pageTree.clear()
        pageTree.add(PageHeader("Hi Mom!", true))
        pageTree.add(PageHeader("Oh, and Dad, too!", false, 1))
    }

    fun expanded(pageHeader: PageHeader, nowExpanded: Boolean) {
        println("Expanded -> ${pageHeader.title} = $nowExpanded")
    }

    fun expanded(uiTreeNode: UiTreeNode, nowExpanded: Boolean) {
        if (nowExpanded) {
            uiTreeNode.expandedState.value = ExpandedState.Open
        } else {
            uiTreeNode.expandedState.value = ExpandedState.Closed
        }
        println("Expanded -> ${uiTreeNode.title} = $nowExpanded")
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            uiTree.children.add(
                UiTreeNode("Parent 1").apply {
                    expandedState.value = ExpandedState.Open
                    children.add(UiTreeNode("Child 1"))
                    children.add(UiTreeNode("Child 2"))
                }
            )
            uiTree.children.add(
                UiTreeNode("Parent 2").apply {
                    expandedState.value = ExpandedState.Closed
                    children.add(UiTreeNode("Child 3"))
                    children.add(UiTreeNode("Child 4"))
                }
            )
            pageTree.clear()
            pageTree.add(connection.fetchUserRoot())
        }
    }
}