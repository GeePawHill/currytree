package org.currytree

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

class NormalModel(block: NormalBlock) {
    val text: MutableState<TextFieldValue>
    val builder = AnnotatedString.Builder(block.text)

    init {
        applyBaseStyle()
        applyInlineStyles(block)
        text = finalizeAnnotatedString()
    }

    private fun finalizeAnnotatedString() = mutableStateOf(TextFieldValue(builder.toAnnotatedString()))

    private fun applyInlineStyles(block: NormalBlock) {
        for (span in block.spans) builder.addStyle(styleSpanFor(span), span.start, span.end)
    }

    private fun applyBaseStyle() {
        builder.addStyle(Styles.normalBase, 0, builder.length)
    }

    private fun styleSpanFor(span: InlineStyleSpan): SpanStyle {
        val toApply = when (span.style) {
            InlineStyle.bold -> Styles.normalBold
            InlineStyle.code -> Styles.normalCode
            InlineStyle.underline -> Styles.normalUnderline
            InlineStyle.italic -> Styles.normalItalic
        }
        return toApply
    }
}

object Styles {
    val normalUnderline = SpanStyle(textDecoration = TextDecoration.Underline)
    val normalBold = SpanStyle(
        fontWeight = FontWeight.Bold
    )
    val normalItalic = SpanStyle(fontStyle = FontStyle.Italic)

    val normalCode = SpanStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.ExtraBold,
        color = Color.Red,
        background = Color.LightGray,
    )

    val normalFontSize = TextUnit(20f, TextUnitType.Sp)
    val normalBase = SpanStyle(fontSize = Styles.normalFontSize)
    val normalPadding = PaddingValues(16.dp, 4.dp, 16.dp, 8.dp)
}
