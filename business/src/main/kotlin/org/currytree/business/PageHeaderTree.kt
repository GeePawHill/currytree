package org.currytree.business

import org.currytree.PageHeader
import org.currytree.tree.BasicTree
import org.currytree.tree.Tree
import org.currytree.tree.TreeLocation
import org.currytree.tree.TreeNode

typealias PageHeaderNode = TreeNode<PageHeader>

class SlugNotFoundException(val slug: String) : RuntimeException("Slug [$slug] not found.")

class PageHeaderTree(
    val base: Tree<PageHeader> = BasicTree(PageHeader("root", true))
) : Tree<PageHeader> by base {

    init {
        val grandparent = PageHeader("Grandparent 1", true)
        add("root", grandparent)
        val parent1 = PageHeader("Parent 1", true)
        add(grandparent.slug, parent1)
        add(parent1.slug, PageHeader("Child 1"))
        add(parent1.slug, PageHeader("Child 2"))
        val parent2 = PageHeader("Parent 2", true)
        add("root", parent2)
        add(parent2.slug, PageHeader("Child 3"))
        add(parent2.slug, PageHeader("Child 4"))
    }

    fun find(slug: String): TreeLocation<PageHeader> {
        return find { slug == it.slug }
    }

    fun add(parent: String, header: PageHeader) {
        val found = find(parent)
        if (!found.isValid()) throw SlugNotFoundException(parent)
        found.leafNode.add(header)
    }

    fun childrenFor(parent: String): List<PageHeader> {
        val found = find(parent)
        if (!found.isValid()) throw SlugNotFoundException(parent)
        return found.leafNode.children.map { it.data }
    }
}