package org.currytree.business

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.nio.file.Path

class RamBodyStoreTest {
    val store = RamBodyStore()

    @Test
    fun `read of non-existent body throws`() {
        assertThrows(BodyNotFound::class.java) {
            store.read(Path.of("."))
        }
    }
}