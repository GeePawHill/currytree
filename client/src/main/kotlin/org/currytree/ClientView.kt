package org.currytree

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import compose.icons.fontawesomeicons.SolidGroup
import compose.icons.fontawesomeicons.solid.CaretDown
import compose.icons.fontawesomeicons.solid.CaretRight

@Composable
fun ClientView(model: ClientModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            TopAppBar {
                Button({}) {
                    Text("TOOL")
                }
            }
        }
        Row(modifier = Modifier.defaultMinSize(800.dp, 400.dp)) {
            Column(modifier = Modifier.weight(0.3f, true)) {
                UiTreeView(model)
            }
            Column(modifier = Modifier.weight(0.5f, true)) {
                Text("BODY")
            }
            Column(modifier = Modifier.weight(0.2f, true)) {
                Row(Modifier.height(150.dp)) {
                    Text("TOC")
                }
                Row() {
                    Column {
                        Button({}, modifier = Modifier.width(200.dp)) {
                            Text("Action 1")
                        }
                        Button({}, Modifier.width(200.dp)) {
                            Text("Action 2")
                        }
                        Button({}, Modifier.width(200.dp)) {
                            Text("Action 3")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UiTreeView(model: ClientModel) {
    LazyColumn {
        items(model.uiTree.children) {
            UiTreeItem(it, model::expanded)
        }
    }
}

@Composable
fun UiTreeItem(item: UiTreeNode, onExpanded: (node: UiTreeNode, nowExpanded: Boolean) -> Unit) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ExpanderIcon(item.expandedState) {
                onExpanded(item, it)
            }
            Text(
                item.title,
                Modifier.padding(4.dp),
                fontSize = TextUnit(20f, TextUnitType.Sp)
            )
        }
        if (item.expandedState.value == ExpandedState.Open) {
            item.children.forEach {
                Row {
                    Spacer(Modifier.size(EXPANDER_SIZE, EXPANDER_SIZE))
                    UiTreeItem(it, onExpanded)
                }
            }
        }
    }
}

@Composable
private fun ExpanderIcon(
    expandedState: MutableState<ExpandedState>,
    onExpanded: (nowExpanded: Boolean) -> Unit
) {
    when (expandedState.value) {
        ExpandedState.None ->
            Spacer(Modifier.size(EXPANDER_SIZE, EXPANDER_SIZE))

        ExpandedState.Open ->
            Icon(
                SolidGroup.CaretDown,
                "Expand Button Expanded",
                Modifier
                    .size(EXPANDER_SIZE)
                    .padding(4.dp)
                    .clickable {
                        onExpanded(false)
                    }
            )

        ExpandedState.Closed ->
            Icon(
                SolidGroup.CaretRight,
                "Expand Button Expanded",
                Modifier
                    .size(EXPANDER_SIZE)
                    .padding(4.dp)
                    .clickable {
                        onExpanded(true)
                    }
            )
    }
}

val EXPANDER_SIZE = 28.dp