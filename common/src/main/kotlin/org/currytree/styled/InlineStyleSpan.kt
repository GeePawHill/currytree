package org.currytree.styled

import kotlinx.serialization.Serializable

@Serializable
data class InlineStyleSpan(val start: Int, val end: Int, val style: InlineStyle)