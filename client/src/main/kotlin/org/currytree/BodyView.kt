package org.currytree

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.currytree.normal.BlockModel
import org.currytree.code.CodeModel
import org.currytree.normal.NormalModel
import org.currytree.styled.Styles

@Composable
fun BodyView(body: SnapshotStateList<BlockModel>) {
    LazyColumn {
        items(body) {
            when (it) {
                is NormalModel -> NormalView(it)
                is CodeModel -> CodeView(it)
            }
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

@Composable
fun CodeView(model: CodeModel) {
    Row(
        Modifier.padding(20.dp)
            .border(1.dp, Color.Red)
    ) {
        BasicTextField(
            model.code.value,
            { new ->
                model.code.value = new
            },
            Modifier
                .padding(Styles.normalPadding),
            enabled = true,
            readOnly = false,
        )
    }
}
