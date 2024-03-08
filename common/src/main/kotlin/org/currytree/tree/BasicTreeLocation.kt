package org.currytree.tree

class BasicTreeLocation<ITEM>(override val nodes: List<TreeNode<ITEM>>) : TreeLocation<ITEM> {
    override val items: List<ITEM> get() = nodes.map { it.data }
    override val parentNode: TreeNode<ITEM> get() = nodes.dropLast(1).last()
    override val parent: ITEM get() = parentNode.data
    override val leafNode: TreeNode<ITEM> get() = nodes.last()
    override val leaf: ITEM get() = leafNode.data

    override fun isValid(): Boolean = nodes.isNotEmpty()

    constructor(node: TreeNode<ITEM>, other: TreeLocation<ITEM>) :
            this(mutableListOf(node).apply { addAll(other.nodes) })
}