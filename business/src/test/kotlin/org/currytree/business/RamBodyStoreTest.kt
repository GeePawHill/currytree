package org.currytree.business

import org.assertj.core.api.Assertions.assertThat
import org.currytree.PageBody
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.nio.file.Path

class RamBodyStoreTest {
    val store = RamBodyStore()
    val idunnoPath = Path.of("idunno")
    val idunnoBody = PageBody("idunno", emptyList())

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
}