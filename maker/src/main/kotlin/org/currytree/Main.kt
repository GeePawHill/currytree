package org.currytree

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.currytree.business.CurryTree
import org.currytree.business.UserRamStore

fun main() {
    val users = UserRamStore()
    val curryTree = CurryTree(users)
    curryTree.initializeForMaker()
    val connection = DirectConnection(curryTree)
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
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            Button({ }) {
                Text("Change it.", color = Color.Yellow)
            }
        }
        ClientView(model)
    }
}