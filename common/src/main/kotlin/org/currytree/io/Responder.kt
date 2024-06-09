package org.currytree.io

interface Responder {
    fun <T : Any> ok(body: T)
    fun ok()
    fun forbidden(message: String)
    fun conflict(message: String)

    companion object {
        const val OK = 200
        const val CONFLICT = 409
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val BAD_REQUEST = 400
    }
}