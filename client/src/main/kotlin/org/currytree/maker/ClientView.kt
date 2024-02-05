package org.currytree.maker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import compose.icons.fontawesomeicons.SolidGroup
import compose.icons.fontawesomeicons.solid.CaretDown
import compose.icons.fontawesomeicons.solid.CaretRight
import compose.icons.fontawesomeicons.solid.Check

@Composable
fun ClientView(model: ClientModel) {
    Column(modifier = Modifier.width(800.dp)) {
        Row {
            TopAppBar {
                Button({}) {
                    Text("TOOL")
                }
            }
        }
        Row(modifier = Modifier.defaultMinSize(800.dp, 400.dp)) {
            Column(modifier = Modifier.weight(0.3f, true)) {
                val headers = remember { model.pageTree }
                LazyColumn {
                    items(headers) {
                        TreeViewItem(it)
                        { pageHeader, nowExpanded -> model.expanded(pageHeader, nowExpanded) }
                    }
                }
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
fun TreeViewItem(item: PageHeader, onExpanded: (pageHeader: PageHeader, nowExpanded: Boolean) -> Unit) {
    Row {
        val isExpanded = remember { mutableStateOf(false) }
        (0 until item.depth).forEach {
            Spacer(Modifier.size(28.dp, 28.dp))
        }
        ExpanderIcon(isExpanded, onExpanded, item)
        Icon(
            SolidGroup.Check,
            "Checkmark",
            Modifier
                .size(28.dp)
                .padding(4.dp)
        )
        Text(
            item.title,
            Modifier.padding(4.dp),
            fontSize = TextUnit(20f, TextUnitType.Sp)
        )
    }
}

@Composable
private fun ExpanderIcon(
    isExpanded: MutableState<Boolean>,
    onExpanded: (pageHeader: PageHeader, nowExpanded: Boolean) -> Unit,
    item: PageHeader
) {
    if (isExpanded.value) {
        Icon(
            SolidGroup.CaretDown,
            "Expand Button Expanded",
            Modifier
                .size(28.dp)
                .padding(4.dp)
                .clickable {
                    println("Hey.")
                    isExpanded.value = !isExpanded.value
                    onExpanded(item, isExpanded.value)
                }
        )
    } else {
        if (item.hasChildren) {
            Icon(
                SolidGroup.CaretRight,
                "Expand Button Expanded",
                Modifier
                    .size(28.dp)
                    .padding(4.dp)
                    .clickable {
                        println("Hey.")
                        isExpanded.value = !isExpanded.value
                        onExpanded(item, isExpanded.value)
                    }
            )
        } else {
            Spacer(Modifier.size(28.dp, 28.dp))
        }
    }
}