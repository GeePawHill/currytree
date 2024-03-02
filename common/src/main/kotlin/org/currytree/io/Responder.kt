package org.currytree.io

interface Responder {
    fun <T : Any> ok(body: T)
    fun ok()
}