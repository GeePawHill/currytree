package org.currytree

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable

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
    BasicTextField(
        normal.text.value,
        { },
        enabled = true,
        readOnly = true
    )
}