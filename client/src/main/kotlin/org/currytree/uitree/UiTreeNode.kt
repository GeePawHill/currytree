package org.currytree.uitree

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class UiTreeNode(val title: String) {
    val expandedState = mutableStateOf(ExpandedState.None)
    val children = mutableStateListOf<UiTreeNode>()
}

