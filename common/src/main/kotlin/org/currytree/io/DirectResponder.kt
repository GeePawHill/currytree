package org.currytree.io

data class Response<T : Any>(val code: Int, val body: T)

class DirectResponder : Responder {

    val responses = mutableListOf<Response<Any>>()

    val lastCode: Int get() = responses.last().code
    val lastBody get() = responses.last().body
    val lastMessage: String get() = lastBody.toString()


    override fun <T : Any> ok(body: T) {
        responses.add(Response(200, body))
    }

    override fun ok() {
        responses.add(Response(200, ""))
    }

    fun error(code: Int, message: String) {
        responses.add(Response(code, message))
    }

    override fun forbidden(message: String) = error(403, message)

    override fun conflict(message: String) = error(409, message)
}
