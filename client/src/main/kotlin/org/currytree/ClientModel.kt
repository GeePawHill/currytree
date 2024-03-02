package org.currytree

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.currytree.blocks.Block
import org.currytree.blocks.BlockModelFactory
import org.currytree.blocks.CodeBlock
import org.currytree.blocks.NormalBlock
import org.currytree.code.CodeModel
import org.currytree.normal.BlockModel
import org.currytree.normal.NormalModel
import org.currytree.uitree.ExpandedState
import org.currytree.uitree.UiTreeNode


class LocalBlockModelFactory : BlockModelFactory {
    val body = mutableListOf<BlockModel>()

    override fun accept(block: NormalBlock) {
        body.add(NormalModel(block))
    }

    override fun accept(block: CodeBlock) {
        body.add(CodeModel(block))
    }

    fun makeBodyFrom(bodyFor: List<Block>) {
        body.clear()
        bodyFor.forEach { it.callMe(this) }
    }
}

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
            val newChildren = connection.childrenFor(uiTreeNode.item.slug).map {
                UiTreeNode(it).apply {
                    if (it.hasChildren) expandedState.value = ExpandedState.Closed
                }
            }
            withContext(Dispatchers.Main) {
                uiTreeNode.children.clear()
                uiTreeNode.children.addAll(newChildren)
            }
        }
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
