package org.currytree

import androidx.compose.runtime.mutableStateListOf
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
    val body = mutableStateListOf<NormalModel>()

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
    }

    init {
        refreshChildren(uiTree)
        body.add(NormalModel(NormalBlock("Hi Mom!")))
        body.add(
            NormalModel(
                NormalBlock("This is purposefully a longer block so we can see the effect of the paragraph padding that it needs to have.")
            )
        )
        body.add(
            NormalModel(
                NormalBlock(
                    "Every word is different.",
                    listOf(
                        InlineStyleSpan(0, 5, InlineStyle.bold),
                        InlineStyleSpan(6, 10, InlineStyle.italic),
                        InlineStyleSpan(11, 13, InlineStyle.underline),
                        InlineStyleSpan(14, 23, InlineStyle.code)
                    )
                )
            )
        )
    }
}
