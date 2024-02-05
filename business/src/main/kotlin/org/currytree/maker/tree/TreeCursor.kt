package org.currytree.maker.tree

interface TreeCursor<ITEM> {
    val items: List<ITEM>
    val nodes: List<TreeNode<ITEM>>
    val parentNode: TreeNode<ITEM>
    val parent: ITEM
    val leafNode: TreeNode<ITEM>
    val leaf: ITEM
    fun isValid(): Boolean
}

