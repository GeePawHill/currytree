package org.currytree

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.currytree.normal.BlockModel
import org.currytree.blocks.BlockModelFactory
import org.currytree.blocks.CodeBlock
import org.currytree.code.CodeModel
import org.currytree.blocks.NormalBlock
import org.currytree.normal.NormalModel
import org.currytree.uitree.ExpandedState
import org.currytree.uitree.UiTreeNode

class ClientModel(val connection: Connection) : BlockModelFactory {
    val selectedBody = mutableStateOf("root")
    val uiTree = UiTreeNode(PageHeader("root", true, "root"))
    val selected = mutableStateOf(uiTree)
    val body = mutableStateListOf<BlockModel>()

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
            ExpandedState.Closed -> {
                refreshChildren(uiTreeNode)
                uiTreeNode.expandedState.value = ExpandedState.Open
            }
        }
    }

    fun refreshChildren(uiTreeNode: UiTreeNode<PageHeader>) {
        CoroutineScope(Dispatchers.IO).launch {
            val toAdd = connection.childrenFor(uiTreeNode.item.slug)
            uiTreeNode.children.clear()
            for (header in toAdd) {
                val new = UiTreeNode(header)
                if (header.hasChildren) {
                    new.expandedState.value = ExpandedState.Closed
                }
                uiTreeNode.children.add(new)
            }
        }
    }

    fun select(uiTreeNode: UiTreeNode<PageHeader>) {
        selected.value.isSelected.value = false
        uiTreeNode.isSelected.value = true
        selected.value = uiTreeNode
        selectedBody.value = uiTreeNode.item.toString()
        CoroutineScope(Dispatchers.IO).launch {
            loadBody(uiTreeNode)
        }
    }

    private suspend fun loadBody(uiTreeNode: UiTreeNode<PageHeader>) {
        body.clear()
        for (block in connection.bodyFor(uiTreeNode.item.slug)) {
            block.callMe(this)
        }
    }

    init {
        refreshChildren(uiTree)
    }

    override fun accept(block: NormalBlock) {
        body.add(NormalModel(block))
    }

    override fun accept(block: CodeBlock) {
        body.add(CodeModel(block))
    }
}
