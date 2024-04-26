package org.currytree.business.store

import org.assertj.core.api.Assertions.assertThat
import org.currytree.business.PageHeaderTree
import org.currytree.business.PageHeaderTreeTest.Companion.initForDemo
import org.junit.jupiter.api.Test
import java.nio.file.Path

class PageTreeRamStoreTest {
    val store = PageTreeRamStore()
    val idunnoPath = Path.of("idunno")
    val idunnoTree = PageHeaderTree().apply {
        initForDemo(this)
    }

    @Test
    fun `simple round trip works`() {
        store.write(idunnoPath, idunnoTree)
        val asRead = store.read(idunnoPath)
        assertThat(asRead).isEqualTo(idunnoTree)
    }
}