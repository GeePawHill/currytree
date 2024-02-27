package org.currytree

import androidx.compose.runtime.mutableStateOf
import org.currytree.styled.Styles

class NormalModel(block: NormalBlock) {
    val text = mutableStateOf(Styles.normalStyler.style(block.field))
}

