package org.currytree

import org.assertj.core.api.Assertions.assertThat
import org.currytree.io.DirectResponder
import org.currytree.io.Response
import org.junit.jupiter.api.Test

class DirectResponderTest {

    val responder = DirectResponder()

    @Test
    fun `handles ok with no argument`() {
        responder.ok()
        assertThat(responder.responses).containsExactly(Response(200, ""))
    }

    @Test
    fun `handles ok with String argument`() {
        responder.ok("That was good.")
        assertThat(responder.responses).containsExactly(Response(200, "That was good."))
    }

    @Test
    fun `handles ok with PageHeader argument`() {
        val result = PageHeader("Title", false, "title")
        responder.ok(result)
        assertThat(responder.responses).containsExactly(Response(200, result))
    }
}