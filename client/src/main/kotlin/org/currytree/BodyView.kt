package org.currytree

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import org.currytree.styled.Styles

@Composable
fun BodyView(body: SnapshotStateList<NormalModel>) {
    LazyColumn {
        items(body) {
            NormalView(it)
        }
    }
}

@Composable
fun NormalView(normal: NormalModel) {
    BasicTextField(
        normal.text.value,
        { new ->
            normal.text.value = new
        },
        Modifier.padding(Styles.normalPadding),
        enabled = true,
        readOnly = false,
    )
}
