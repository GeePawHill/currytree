package org.currytree.tree

import java.util.function.Predicate

interface Tree<ITEM> {
    val root: TreeNode<ITEM>
    fun visit(visitor: TreeVisitor<ITEM>)
    fun find(predicate: Predicate<ITEM>): TreeLocation<ITEM>
}