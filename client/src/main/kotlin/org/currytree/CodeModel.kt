package org.currytree

import androidx.compose.runtime.mutableStateOf
import org.currytree.styled.Styles

class CodeModel(block: CodeBlock) : BlockModel {
    val code = mutableStateOf(Styles.codeStyler.style(block.field))
}

