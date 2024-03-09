package org.currytree.business

import org.assertj.core.api.Assertions.assertThat
import org.currytree.PageBody
import org.currytree.blocks.CodeBlock
import org.currytree.blocks.NormalBlock
import org.currytree.styled.StyledField
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.nio.file.Path

class RamBodyStoreTest {
    val store = RamBodyStore()
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
    fun `read of non-existent body throws`() {
        assertThrows(BodyNotFound::class.java) {
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