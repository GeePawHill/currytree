package org.currytree.maker.tree

interface TreeVisitor<ITEM> {
    fun enterParent(node: TreeNode<ITEM>)
    fun leaveParent(node: TreeNode<ITEM>)
    fun leaf(node: TreeNode<ITEM>)
}