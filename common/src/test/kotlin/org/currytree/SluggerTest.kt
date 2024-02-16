package org.currytree


import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test

class SluggerTest {
    val slugger = Slugger

    @Test
    fun `drops illegal characters`() {
        assertThat(slugger.slugify("a~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/b")).isEqualTo("ab")
    }

    @Test
    fun `returns lower case`() {
        assertThat(slugger.slugify("AB")).isEqualTo("ab")
    }

    @Test
    fun `stripsControl`() {
        assertThat(slugger.slugify("A\nb")).isEqualTo("ab")
    }

    @Test
    fun `splits words`() {
        assertThat(slugger.slugify("A   b")).isEqualTo("a-b")
    }

    @Test
    fun `non-ascii`() {
        val nonAscii = Char(176)
        assertThat(slugger.slugify("a${nonAscii}b")).isEqualTo("ab")
    }

    @Test
    fun `it is a wonderful life`() {
        assertThat(slugger.slugify("`It's a wonderful life!`")).isEqualTo("its-a-wonderful-life")
    }
}