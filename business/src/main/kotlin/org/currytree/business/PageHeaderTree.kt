package org.currytree.business

import kotlinx.serialization.Serializable
import org.currytree.PageHeader
import org.currytree.tree.TreeLocation
import org.currytree.tree.TreeNode
import java.util.function.Predicate

@Serializable
data class PageHeaderTree(
    private val root: TreeNode<PageHeader> = TreeNode(PageHeader("root", true))
) {

    fun find(predicate: Predicate<PageHeader>): TreeLocation<PageHeader> {
        return root.find { predicate.test(it) }
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