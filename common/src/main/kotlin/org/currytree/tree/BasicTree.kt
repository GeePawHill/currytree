package org.currytree.tree

import java.util.function.Predicate


class BasicTree<ITEM>(rootItem: ITEM) : Tree<ITEM> {
    override val root = TreeNode(rootItem)

    override fun visit(visitor: TreeVisitor<ITEM>) {
        root.visit(visitor)
    }

    override fun find(predicate: Predicate<ITEM>): TreeCursor<ITEM> {
        return root.find { predicate.test(it) }
    }
}