package org.currytree.styled

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import org.currytree.code.CodeStyler
import org.currytree.normal.NormalStyler

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
    val normalBase = SpanStyle(fontSize = normalFontSize)
    val normalPadding = PaddingValues(16.dp, 4.dp, 16.dp, 8.dp)
    val codeBase = SpanStyle(fontFamily = FontFamily.Monospace, fontSize = normalFontSize)

    val normalStyler = NormalStyler()
    val codeStyler = CodeStyler()
}