package org.currytree.uitree

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

data class UiTreeNode<ITEM>(val item: ITEM) {
    val expandedState = mutableStateOf(ExpandedState.None)
    val isSelected = mutableStateOf(false)
    val children = mutableStateListOf<UiTreeNode<ITEM>>()

    fun dump(indent: Int = 0) {
        (0 until indent).forEach { print("  ") }
        for (child in children) child.dump(indent + 1)
    }
}

