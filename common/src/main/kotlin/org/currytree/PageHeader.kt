package org.currytree

import kotlinx.serialization.Serializable

@Serializable
data class PageHeader(val title: String, val hasChildren: Boolean = false, val depth: Int = 0) {
    companion object {
        val PENDING = PageHeader("...")
    }
}