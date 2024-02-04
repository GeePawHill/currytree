package org.currytree.maker

import kotlinx.serialization.Serializable

@Serializable
data class PageHeader(val title: String) {
    companion object {
        val PENDING = PageHeader("...")
    }
}