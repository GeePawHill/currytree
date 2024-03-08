package org.currytree.utility

fun <T> MutableCollection<T>.reloadFrom(source: Collection<T>) {
    clear()
    addAll(source)
}
