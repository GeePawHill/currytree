package org.currytree

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue

class NormalModel(block: NormalBlock) {
    val text: MutableState<TextFieldValue>

    init {
        text = mutableStateOf(TextFieldValue(block.text))
    }
}
