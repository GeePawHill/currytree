package org.currytree.business

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Path

class PageTreeRamStoreTest {
    val store = PageTreeRamStore()
    val idunnoPath = Path.of("idunno")
    val idunnoTree = PageHeaderTree().apply {
        PageHeaderTree.initForDemo(this)
    }

    @Test
    fun `simple round trip works`() {
        store.write(idunnoPath, idunnoTree)
        val asRead = store.read(idunnoPath)
        assertThat(asRead).isEqualTo(idunnoTree)
    }
}