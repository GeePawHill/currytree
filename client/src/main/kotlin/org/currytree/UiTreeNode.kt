package org.currytree

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class UiTreeNode {
    val isExpanded = mutableStateOf(true)
    val children = mutableStateListOf<UiTreeNode>()
}