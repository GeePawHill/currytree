package org.currytree.business

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.currytree.PageBody
import java.nio.file.Path

class RamBodyStore : BodyStore {

    private val pathToBody = mutableMapOf<Path, String>()

    override fun write(path: Path, body: PageBody) {
        val bodyString = Json.encodeToString(body)
        pathToBody.put(path, bodyString)
    }

    override fun read(path: Path): PageBody {
        val bodyString = pathToBody.getOrElse(path) { throw BodyNotFound(path) }
        return Json.decodeFromString(bodyString)
    }

    override fun exists(path: Path): Boolean {
        return pathToBody[path] != null
    }
}