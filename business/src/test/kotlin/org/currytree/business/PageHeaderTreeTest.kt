package org.currytree.business

import org.assertj.core.api.Assertions.assertThat
import org.currytree.PageHeader
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class PageHeaderTreeTest {
    val tree = PageHeaderTree().apply {
        initForDemo(this)
    }

    @Test
    fun `find by slug`() {
        assertThat(tree.find("non-existent").isValid()).isFalse()
        assertThat(tree.find("root").isValid()).isTrue()
    }

    @Test
    fun `childrenFor slug and tree initialization`() {
        assertThat(tree.childrenFor("root").map { it.slug }).containsExactly(
            "grandparent-1",
            "parent-2"
        )
    }

    @Test
    fun `childrenFor bad slug fails`() {
        assertThrows(SlugNotFoundException::class.java) {
            tree.childrenFor("non-existent")
        }
    }

    companion object {

        fun initForDemo(tree: PageHeaderTree) {
            val grandparent = PageHeader("Grandparent 1", true)
            tree.add("root", grandparent)
            val parent1 = PageHeader("Parent 1", true)
            tree.add(grandparent.slug, parent1)
            tree.add(parent1.slug, PageHeader("Child 1"))
            tree.add(parent1.slug, PageHeader("Child 2"))
            val parent2 = PageHeader("Parent 2", true)
            tree.add("root", parent2)
            tree.add(parent2.slug, PageHeader("Child 3"))
            tree.add(parent2.slug, PageHeader("Child 4"))
        }
    }
}