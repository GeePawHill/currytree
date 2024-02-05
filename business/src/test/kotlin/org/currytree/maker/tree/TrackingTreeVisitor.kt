package org.currytree.maker.tree

class TrackingTreeVisitor<ITEM> : TreeVisitor<ITEM> {

    val visited = mutableListOf<ITEM>()

    override fun enterParent(node: TreeNode<ITEM>) {
        visited += node.data
    }

    override fun leaveParent(node: TreeNode<ITEM>) {
        visited += node.data
    }

    override fun leaf(node: TreeNode<ITEM>) {
        visited += node.data
    }
}