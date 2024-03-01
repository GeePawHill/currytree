package org.currytree.styled

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.TextFieldValue
import org.currytree.InlineStyle
import org.currytree.InlineStyleSpan
import org.currytree.StyledField

class CodeStyler {

    private var builder = AnnotatedString.Builder("")

    fun style(field: StyledField): TextFieldValue {
        builder = AnnotatedString.Builder(field.text)
        applyBaseStyle()
        applyInlineStyles(field)
        return finalizeTextFieldValue()
    }

    private fun finalizeTextFieldValue() = TextFieldValue(builder.toAnnotatedString())

    private fun applyInlineStyles(field: StyledField) {
        for (span in field.spans) builder.addStyle(styleSpanFor(span), span.start, span.end)
    }

    private fun applyBaseStyle() {
        builder.addStyle(Styles.codeBase, 0, builder.length)
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