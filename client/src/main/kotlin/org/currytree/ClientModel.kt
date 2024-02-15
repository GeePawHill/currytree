package org.currytree

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.currytree.uitree.ExpandedState
import org.currytree.uitree.UiTreeNode

class ClientModel(val connection: Connection) {
    val pageTree = mutableStateListOf(PageHeader.PENDING)
    val uiTree = UiTreeNode("root")
    val selected = mutableStateOf(uiTree)

    fun changeWelcome() {
        pageTree.clear()
        pageTree.add(PageHeader("Hi Mom!", true))
        pageTree.add(PageHeader("Oh, and Dad, too!", false, 1))
    }

    fun expanded(uiTreeNode: UiTreeNode) {
        when (uiTreeNode.expandedState.value) {
            ExpandedState.None -> {}
            ExpandedState.Open -> uiTreeNode.expandedState.value = ExpandedState.Closed
            ExpandedState.Closed -> uiTreeNode.expandedState.value = ExpandedState.Open
        }
    }

    fun select(uiTreeNode: UiTreeNode) {
        selected.value.isSelected.value = false
        uiTreeNode.isSelected.value = true
        selected.value = uiTreeNode
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            uiTree.children.add(
                UiTreeNode("Grandparent 1").apply {
                    expandedState.value = ExpandedState.Open
                    children.add(UiTreeNode("Parent 1").apply {
                        expandedState.value = ExpandedState.Closed
                        children.add(UiTreeNode("Child 1"))
                        children.add(UiTreeNode("Child 2"))
                    })
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
