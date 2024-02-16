package org.currytree

import kotlinx.serialization.Serializable

@Serializable
data class PageHeader(
    val title: String,
    val hasChildren: Boolean = false,
    val slug: String = Slugger.slugify(title)
) {
    companion object {
        val PENDING = PageHeader("...")
    }
}