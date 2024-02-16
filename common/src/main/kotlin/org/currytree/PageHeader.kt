package org.currytree

import kotlinx.serialization.Serializable

@Serializable
data class PageHeader(val title: String, val slug: String = Slugger.slugify(title), val hasChildren: Boolean = false) {
    companion object {
        val PENDING = PageHeader("...")
    }
}