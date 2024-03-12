package org.currytree.business

import java.nio.file.Path

interface ValueStore<VALUE> {
    fun exists(path: Path): Boolean
    fun write(path: Path, value: VALUE)
    fun read(path: Path): VALUE
}


