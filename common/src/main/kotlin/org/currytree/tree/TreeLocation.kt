package org.currytree.tree

interface TreeLocation<ITEM> {
    val items: List<ITEM>
    val nodes: List<TreeNode<ITEM>>
    val parentNode: TreeNode<ITEM>
    val parent: ITEM
    val leafNode: TreeNode<ITEM>
    val leaf: ITEM
    fun isValid(): Boolean
}

