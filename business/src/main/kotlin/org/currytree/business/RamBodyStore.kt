package org.currytree.business

import org.currytree.PageBody
import java.nio.file.Path

class RamBodyStore : BodyStore {
    override fun write(path: Path, body: PageBody) {

    }

    override fun read(path: Path): PageBody {
        throw BodyNotFound(path)
    }

    override fun exists(path: Path): Boolean {
        return false
    }
}