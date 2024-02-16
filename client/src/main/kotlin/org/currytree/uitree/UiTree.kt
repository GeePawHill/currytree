package org.currytree.uitree

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import compose.icons.fontawesomeicons.SolidGroup
import compose.icons.fontawesomeicons.solid.CaretDown
import compose.icons.fontawesomeicons.solid.CaretRight
import org.currytree.ClientModel


@Composable
fun UiTreeView(model: ClientModel, body: @Composable (item: UiTreeNode) -> Unit) {
    LazyColumn {
        items(model.uiTree.children) {
            UiTreeItem(0, it, model::expanded, model::select, body)
        }
    }
}


@Composable
fun UiTreeItem(
    indent: Int,
    item: UiTreeNode,
    onExpanded: (node: UiTreeNode) -> Unit,
    onSelected: (node: UiTreeNode) -> Unit,
    body: @Composable (item: UiTreeNode) -> Unit
) {
    Column {
        val modifier = selectableModifier(onSelected, item)
        Row(modifier, verticalAlignment = Alignment.CenterVertically) {
            TreeIndentation(indent)
            TreeExpander(item.expandedState) {
                onExpanded(item)
            }
            body(item)
        }
        if (item.expandedState.value == ExpandedState.Open) {
            item.children.forEach {
                UiTreeItem(indent + 1, it, onExpanded, onSelected, body)
            }
        }
    }
}

private fun selectableModifier(
    onSelected: (node: UiTreeNode) -> Unit,
    item: UiTreeNode
): Modifier {
    var modifier = Modifier
        .clickable {
            onSelected(item)
        }
        .fillMaxWidth()
    if (item.isSelected.value) modifier = Modifier.background(Color.LightGray)
    return modifier
}

@Composable
private fun TreeIndentation(indent: Int) {
    Spacer(Modifier.width((indent * INDENT_WIDTH).dp))
}

@Composable
private fun TreeExpander(
    expandedState: MutableState<ExpandedState>,
    onExpanded: () -> Unit
) {
    when (expandedState.value) {
        ExpandedState.None -> NoTreeExpander()
        ExpandedState.Open -> OpenTreeExpander(onExpanded)
        ExpandedState.Closed -> ClosedTreeExpander(onExpanded)
    }
}

@Composable
private fun NoTreeExpander() {
    Spacer(Modifier.size(EXPANDER_SIZE, EXPANDER_SIZE))
}

@Composable
private fun ClosedTreeExpander(onExpanded: () -> Unit) {
    TreeExpanderIcon(SolidGroup.CaretRight, onExpanded)
}

@Composable
private fun OpenTreeExpander(onExpanded: () -> Unit) {
    TreeExpanderIcon(SolidGroup.CaretDown, onExpanded)
}

@Composable
private fun TreeExpanderIcon(
    vector: ImageVector,
    onExpanded: () -> Unit
) {
    Icon(
        vector,
        "Expand Button",
        Modifier
            .size(EXPANDER_SIZE)
            .padding(4.dp)
            .clickable {
                onExpanded()
            }
    )
}

val EXPANDER_SIZE = 28.dp
val INDENT_WIDTH = 15