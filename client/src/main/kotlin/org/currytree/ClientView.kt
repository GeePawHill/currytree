package org.currytree

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import org.currytree.uitree.UiTreeNode
import org.currytree.uitree.UiTreeView

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
                UiTreeView(model.uiTree, model::expanded, model::select) { item ->
                    TreeBody(item)
                }
            }
            Spacer(
                Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(SolidColor(Color.Black), shape = RectangleShape, 0.5f)
            )
            Column(modifier = Modifier.weight(0.5f, true)) {
                BodyView(model.body)
            }
            Spacer(
                Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .background(SolidColor(Color.Black), shape = RectangleShape, 0.5f)
            )
            Column(modifier = Modifier.weight(0.2f, true)) {
                Row(Modifier.height(150.dp)) {
                    Text("TOC Goes Here")
                }
                Row {
                    Column {
                        Button({}, modifier = Modifier.width(200.dp)) {
                            Text("Action 1")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TreeBody(item: UiTreeNode<PageHeader>) {
    Text(
        item.item.title,
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        fontSize = TextUnit(18f, TextUnitType.Sp)
    )
}
