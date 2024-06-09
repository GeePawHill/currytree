package org.currytree.business

import org.assertj.core.api.Assertions.assertThat
import org.currytree.business.store.BodyRamStore
import org.currytree.business.store.CohortRamStore
import org.currytree.business.store.UserRamStore
import org.currytree.io.DirectResponder
import org.junit.jupiter.api.Test

class CurryTreeTest {
    val users = UserRamStore()
    val bodies = BodyRamStore()
    val cohorts = CohortRamStore()
    val curryTree = CurryTree(users, bodies, cohorts)
    val responder = DirectResponder()

    val credentials = UserCredentials("admin", "password", "email")

    @Test
    fun `clean initialize adds site admin`() {
        curryTree.initialize(responder, credentials)
        assertThat(responder.lastCode).isEqualTo(200)
        assertThat(users.exists(credentials.path)).isTrue()
        assertThat(users.read(credentials.path).isSiteAdmin).isTrue()
    }

    @Test
    fun `initialize won't run twice`() {
        curryTree.initialize(responder, credentials)
        curryTree.initialize(responder, credentials)
        assertThat(responder.lastBody as String).isEqualTo("This site is already initialized.")
    }


}