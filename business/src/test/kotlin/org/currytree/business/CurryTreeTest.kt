package org.currytree.business

import org.assertj.core.api.Assertions.assertThat
import org.currytree.business.store.BodyRamStore
import org.currytree.business.store.CohortRamStore
import org.currytree.business.store.UserRamStore
import org.currytree.io.DirectResponder
import org.junit.jupiter.api.Test
import java.nio.file.Path

class CurryTreeTest {
    val users = UserRamStore()
    val bodies = BodyRamStore()
    val cohorts = CohortRamStore()
    val curryTree = CurryTree(users, bodies, cohorts)
    val responder = DirectResponder()

    val credentials = UserCredentials("username", "password", "email")

    @Test
    fun `initialize adds site admin`() {
        curryTree.initialize(responder, credentials)
        assertThat(users.exists(Path.of("username"))).isTrue()

    }

}