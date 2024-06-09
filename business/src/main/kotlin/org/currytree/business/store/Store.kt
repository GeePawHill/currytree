package org.currytree.business.store

import java.nio.file.Path

interface Store<VALUE> {
    fun exists(path: Path): Boolean
    fun write(path: Path, value: VALUE)
    fun read(path: Path): VALUE
    val size: Int
}


