package org.currytree.tree

import kotlinx.serialization.Serializable
import java.util.function.Predicate


@Serializable
class BasicTree<ITEM>(override val root: TreeNode<ITEM>) : Tree<ITEM> {

    constructor(data: ITEM) : this(TreeNode(data))

    override fun visit(visitor: TreeVisitor<ITEM>) {
        root.visit(visitor)
    }

    override fun find(predicate: Predicate<ITEM>): TreeLocation<ITEM> {
        return root.find { predicate.test(it) }
    }
}