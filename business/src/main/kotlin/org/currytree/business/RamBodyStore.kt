package org.currytree.business

import org.currytree.PageBody
import java.nio.file.Path

class RamBodyStore : BodyStore {

    private val pathToBody = mutableMapOf<Path, PageBody>()

    override fun write(path: Path, body: PageBody) {
        pathToBody.put(path, body)
    }

    override fun read(path: Path): PageBody {
        throw BodyNotFound(path)
    }

    override fun exists(path: Path): Boolean {
        return pathToBody[path] != null
    }
}