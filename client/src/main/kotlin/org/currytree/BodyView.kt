package org.currytree

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun BodyView(model: ClientModel) {
    LazyColumn {
        items(model.body) {
            NormalView(it)
        }
    }
}

@Composable
fun NormalView(normal: NormalModel) {
    val text = remember { normal.text }
    BasicTextField(
        text.value,
        { new ->
            text.value = new
        },
        Modifier.padding(Styles.normalPadding),
        enabled = true,
        readOnly = false,
    )
}