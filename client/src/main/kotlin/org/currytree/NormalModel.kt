package org.currytree

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

class NormalModel(block: NormalBlock) {
    val text: MutableState<TextFieldValue>

    init {
        val builder = AnnotatedString.Builder(block.text)
        builder.addStyle(
            SpanStyle(fontSize = Styles.normalFontSize),
            0, builder.length
        )
        text = mutableStateOf(TextFieldValue(builder.toAnnotatedString()))
    }
}

object Styles {
    val normalFontSize = TextUnit(20f, TextUnitType.Sp)
    val normalPadding = PaddingValues(16.dp, 4.dp, 16.dp, 8.dp)
}
