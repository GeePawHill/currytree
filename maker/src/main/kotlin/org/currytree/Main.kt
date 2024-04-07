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
import org.currytree.business.store.UserRamStore

fun main() {
    val curryTree = CurryTree(UserRamStore())
    curryTree.initializeForMaker()
    val model = MakerModel(curryTree)
    application {
        Window(
            onCloseRequest = ::exitApplication
        ) {
            MakerView(model)
        }
    }
}

@Composable
fun MakerView(model: MakerModel) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            Button({ }) {
                Text("Change it.", color = Color.Yellow)
            }
        }
        Row(Modifier.fillMaxWidth()) {
            model.clients.forEach {
                Column(Modifier.fillMaxWidth(.5f)) {
                    ClientView(it)
                }
            }
        }
    }
}