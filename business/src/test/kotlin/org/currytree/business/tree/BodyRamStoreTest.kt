package org.currytree.business.tree

import org.assertj.core.api.Assertions.assertThat
import org.currytree.PageBody
import org.currytree.blocks.CodeBlock
import org.currytree.blocks.NormalBlock
import org.currytree.business.BodyRamStore
import org.currytree.business.ValueNotFound
import org.currytree.styled.StyledField
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.nio.file.Path

class BodyRamStoreTest {
    val store = BodyRamStore()
    val idunnoPath = Path.of("idunno")
    val idunnoBody = PageBody("idunno", emptyList())
    val realPath = Path.of("real")
    val realBody = PageBody(
        "real", listOf(
            NormalBlock(StyledField("Normal")),
            CodeBlock(StyledField("Code"))
        )
    )

    @Test
    fun `exists for non-existent body returns false`() {
        assertThat(store.exists(idunnoPath)).isFalse()
    }

    @Test
    fun `read of non-existent body throws`() {
        assertThrows(ValueNotFound::class.java) {
            store.read(idunnoPath)
        }
    }

    @Test
    fun `after writing, the path exists`() {
        store.write(idunnoPath, idunnoBody)
        assertThat(store.exists(idunnoPath)).isTrue()
    }

    @Test
    fun `write and read roundtrip works`() {
        store.write(realPath, realBody)
        val actual = store.read(realPath)
        assertThat(actual).isEqualTo(realBody)
    }
}