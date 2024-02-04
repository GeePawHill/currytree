package org.currytree.maker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
                        Text(it.title)
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