package org.currytree.code

import androidx.compose.runtime.mutableStateOf
import org.currytree.blocks.CodeBlock
import org.currytree.normal.BlockModel
import org.currytree.styled.Styles

class CodeModel(block: CodeBlock) : BlockModel {
    val code = mutableStateOf(Styles.codeStyler.style(block.field))
}

