package org.currytree.maker.tree

class ChangeSet {
    val childrenChanged = mutableSetOf<String>()
    val bodyChanged = mutableSetOf<String>()
}