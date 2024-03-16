package org.currytree.business

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class PageHeaderTreeTest {
    val tree = PageHeaderTree().apply {
        PageHeaderTree.initForDemo(this)
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
}