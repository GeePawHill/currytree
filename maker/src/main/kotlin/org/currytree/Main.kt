package org.currytree

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.currytree.business.CurryTree

fun main() {
    val curryTree = CurryTree()
    val server = Server(curryTree)
    val connection = DirectConnection(server)
    val model = ClientModel(connection)
    application {
        Window(
            onCloseRequest = ::exitApplication
        ) {
            MakerView(model)
        }
    }
}

@Composable
fun MakerView(model: ClientModel) {
    Column {
        Row {
            Button(model::changeWelcome) {
                Text("Change it.", color = Color.Yellow)
            }
        }
        ClientView(model)
    }
}