package org.currytree.business

import kotlinx.serialization.Serializable

@Serializable
data class User(val handle: String, val isSiteAdmin: Boolean = false, val pages: PageHeaderTree = PageHeaderTree())
