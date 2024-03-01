package org.currytree.normal

import androidx.compose.runtime.mutableStateOf
import org.currytree.blocks.NormalBlock
import org.currytree.styled.Styles

interface BlockModel

class NormalModel(block: NormalBlock) : BlockModel {
    val text = mutableStateOf(Styles.normalStyler.style(block.field))
}

