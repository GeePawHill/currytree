package org.currytree

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.currytree.uitree.ExpandedState
import org.currytree.uitree.UiTreeNode

class ClientModel(val connection: Connection) {
    val selectedBody = mutableStateOf("root")
    val uiTree = UiTreeNode(PageHeader("root", true, "root"))
    val selected = mutableStateOf(uiTree)

    fun changeWelcome() {
//        uiTree.children.add(
//            UiTreeNode("A new title!").apply {
//                expandedState.value = ExpandedState.Closed
//                children.add(UiTreeNode("With a Subpage"))
//            }
//        )
    }

    fun expanded(uiTreeNode: UiTreeNode<PageHeader>) {
        when (uiTreeNode.expandedState.value) {
            ExpandedState.None -> {}
            ExpandedState.Open -> uiTreeNode.expandedState.value = ExpandedState.Closed
            ExpandedState.Closed -> uiTreeNode.expandedState.value = ExpandedState.Open
        }
    }

    fun select(uiTreeNode: UiTreeNode<PageHeader>) {
        selected.value.isSelected.value = false
        uiTreeNode.isSelected.value = true
        selected.value = uiTreeNode
        selectedBody.value = uiTreeNode.item.toString()
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val toAdd = connection.childrenFor("root")
            for (header in toAdd) {
                val new = UiTreeNode(header)
                if (header.hasChildren) {
                    new.expandedState.value = ExpandedState.Closed
                }
                uiTree.children.add(new)
            }
        }
    }
}
