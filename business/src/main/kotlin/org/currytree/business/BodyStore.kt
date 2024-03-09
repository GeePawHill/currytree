package org.currytree.business

import org.currytree.PageBody
import java.nio.file.Path

interface BodyStore {
    fun write(path: Path, body: PageBody)
    fun read(path: Path): PageBody
    fun exists(path: Path): Boolean
}
