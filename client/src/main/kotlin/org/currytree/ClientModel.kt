package org.currytree

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.currytree.normal.BlockModel
import org.currytree.uitree.ExpandedState
import org.currytree.uitree.UiTreeNode
import org.currytree.utility.reloadFrom


class ClientModel(private val connection: Connection) {
    private val factory = LocalBlockModelFactory()
    val uiTree = UiTreeNode(PageHeader("root", true, "root"))
    val selected = mutableStateOf(uiTree)
    val body = mutableStateListOf<BlockModel>()

    fun expanded(uiTreeNode: UiTreeNode<PageHeader>) {
        when (uiTreeNode.expandedState.value) {
            ExpandedState.None -> {}
            ExpandedState.Open -> uiTreeNode.expandedState.value = ExpandedState.Closed
            ExpandedState.Closed -> {
                refreshChildren(uiTreeNode)
                uiTreeNode.expandedState.value = ExpandedState.Open
            }
        }
    }

    private fun refreshChildren(uiTreeNode: UiTreeNode<PageHeader>) {
        CoroutineScope(Dispatchers.IO).launch {
            val newChildren = childrenToTreeNodes(uiTreeNode)
            withContext(Dispatchers.Main) {
                uiTreeNode.children.reloadFrom(newChildren)
            }
        }
    }

    private suspend fun childrenToTreeNodes(uiTreeNode: UiTreeNode<PageHeader>): List<UiTreeNode<PageHeader>> {
        val newChildren = connection.childrenFor(uiTreeNode.item.slug).map {
            UiTreeNode(it).apply {
                if (it.hasChildren) expandedState.value = ExpandedState.Closed
            }
        }
        return newChildren
    }

    fun select(uiTreeNode: UiTreeNode<PageHeader>) {
        selected.value.isSelected.value = false
        uiTreeNode.isSelected.value = true
        selected.value = uiTreeNode
        loadBody(uiTreeNode.item.slug)
    }

    private fun loadBody(slug: String) {
        CoroutineScope(Dispatchers.IO).launch {
            factory.makeBodyFrom(connection.bodyFor(slug))
            withContext(Dispatchers.Main) {
                body.clear()
                body.addAll(factory.body)
            }
        }
    }

    init {
        refreshChildren(uiTree)
    }
}
