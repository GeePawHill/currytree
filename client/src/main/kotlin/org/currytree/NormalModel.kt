package org.currytree

import androidx.compose.runtime.mutableStateOf
import org.currytree.styled.Styles

interface BlockModel

class NormalModel(block: NormalBlock) : BlockModel {
    val text = mutableStateOf(Styles.normalStyler.style(block.field))
}

