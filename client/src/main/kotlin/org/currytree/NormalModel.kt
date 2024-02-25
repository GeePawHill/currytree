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

    init {
        val builder = AnnotatedString.Builder(block.text)
        builder.addStyle(
            SpanStyle(fontSize = Styles.normalFontSize),
            0, builder.length
        )
        for (span in block.styles) {
            when (span.style) {
                InlineStyle.bold -> builder.addStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    span.start, span.end
                )

                InlineStyle.code -> {
                    builder.addStyle(
                        SpanStyle(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Red,
                            background = Color.LightGray,
                        ),
                        span.start, span.end
                    )
                }

                InlineStyle.underline -> builder.addStyle(
                    SpanStyle(textDecoration = TextDecoration.Underline),
                    span.start, span.end
                )

                InlineStyle.italic -> builder.addStyle(
                    SpanStyle(fontStyle = FontStyle.Italic),
                    span.start, span.end
                )

            }
        }
        text = mutableStateOf(TextFieldValue(builder.toAnnotatedString()))
    }
}

object Styles {
    val normalFontSize = TextUnit(20f, TextUnitType.Sp)
    val normalPadding = PaddingValues(16.dp, 4.dp, 16.dp, 8.dp)
}
