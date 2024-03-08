package org.currytree.styled

import kotlinx.serialization.Serializable

@Serializable
data class StyledField(val text: String, val spans: List<InlineStyleSpan> = emptyList())