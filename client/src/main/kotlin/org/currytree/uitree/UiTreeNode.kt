package org.currytree.uitree

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

class UiTreeNode<ITEM>(val item: ITEM) {
    val expandedState = mutableStateOf(ExpandedState.None)
    val isSelected = mutableStateOf(false)
    val children = mutableStateListOf<UiTreeNode<ITEM>>()
}

