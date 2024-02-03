package org.currytree.maker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {
    application {
        Window(
            onCloseRequest = ::exitApplication
        ) {
            ClientView()
        }
    }
}

@Composable
fun ClientView() {
    Column(modifier = Modifier.width(800.dp)) {
    Row {
        TopAppBar {
            Button({}) {
                Text("TOOL")
            }
        }
    }
    Row(modifier = Modifier.defaultMinSize(800.dp,400.dp)) {
        Column(modifier = Modifier.weight(0.3f, true)) {
            Text("Welcome!")
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