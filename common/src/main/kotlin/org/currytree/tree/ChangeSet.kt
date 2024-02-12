package org.currytree.tree

class ChangeSet {
    val childrenChanged = mutableSetOf<String>()
    val bodyChanged = mutableSetOf<String>()
}