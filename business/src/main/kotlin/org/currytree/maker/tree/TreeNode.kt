package org.currytree.maker.tree

import java.util.function.Predicate

data class TreeNode<ITEM>(val data: ITEM) {
    private val _children = mutableListOf<TreeNode<ITEM>>()
    val children: List<TreeNode<ITEM>> get() = _children

    fun add(item: ITEM): TreeNode<ITEM> {
        val new = TreeNode(item)
        _children.add(new)
        return new
    }

    fun add(child: TreeNode<ITEM>, after: TreeNode<ITEM>) {
        val index = _children.indexOf(after)
        if (index == -1) _children.add(child)
        else _children.add(index + 1, child)
    }

    fun update(target: ITEM, replacement: ITEM): TreeNode<ITEM> {
        val index = _children.map { it.data }.indexOf(target)
        val new = TreeNode(replacement)
        _children[index] = new
        return new
    }

    fun remove(child: TreeNode<ITEM>) {
        _children.remove(child)
    }

    fun find(predicate: Predicate<ITEM>): TreeCursor<ITEM> {
        if (predicate.test(this.data)) {
            return BasicTreeCursor(listOf(this))
        }
        for (child in children) {
            val hit = child.find(predicate)
            if (hit.isValid()) {
                return BasicTreeCursor(this, hit)
            }
        }
        return BasicTreeCursor(emptyList())
    }

    fun find(target: ITEM): TreeCursor<ITEM> = find { it == target }

    fun visit(visitor: TreeVisitor<ITEM>) {
        if (children.isEmpty()) {
            visitor.leaf(this)
            return
        }
        visitor.enterParent(this)
        children.forEach {
            it.visit(visitor)
        }
        visitor.leaveParent(this)
    }

    fun print() {
        println(this.data)
    }

    fun isEmpty(): Boolean = _children.isEmpty()
}