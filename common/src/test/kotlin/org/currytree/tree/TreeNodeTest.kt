package org.currytree.tree

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TreeNodeTest {
    val root = TreeNode(0)

    @Test
    fun `add item`() {
        val new = root.add(1)
        assertThat(root.children).contains(new)
    }

    @Test
    fun `add after item`() {
        val first = root.add(1)
        val second = root.add(2)
        val inserted = TreeNode(3)
        root.add(inserted, first)
        assertThat(root.children).containsExactly(first, inserted, second)
    }

    @Test
    fun `remove item`() {
        val first = root.add(1)
        val second = root.add(2)
        root.remove(first)
        assertThat(root.children).containsExactly(second)
    }

    @Test
    fun `find by data`() {
        val first = root.add(1)
        val found = root.find(1)
        assertThat(found.nodes).containsExactly(root, first)
    }

    @Test
    fun `find descendant`() {
        val first = root.add(1)
        val second = first.add(2)
        val found = root.find(2)
        assertThat(found.nodes).containsExactly(root, first, second)
    }

    @Test
    fun `find by predicate`() {
        val first = root.add(1)
        val found = root.find { item -> item == 1 }
        assertThat(found.nodes).containsExactly(root, first)
    }

    @Test
    fun `update`() {
        val first = root.add(2)
        root.update(2, 1)
        val found = root.find(1)
        assertThat(found.items).containsExactly(0, 1)
    }

    @Test
    fun `visit`() {
        val visitor = TrackingTreeVisitor<Int>()
        val first = root.add(1)
        first.add(2)
        first.add(3)
        root.visit(visitor)
        assertThat(visitor.visited).containsExactly(0, 1, 2, 3, 1, 0)
    }
}