package org.currytree

import org.currytree.io.Responder

data class Response<T : Any>(val code: Int, val body: T)

class DirectResponder : Responder {

    val responses = mutableListOf<Response<Any>>()

    val lastBody get() = responses.last().body

    override fun <T : Any> ok(body: T) {
        responses.add(Response(200, body))
    }

    override fun ok() {
        responses.add(Response(200, ""))
    }
}
