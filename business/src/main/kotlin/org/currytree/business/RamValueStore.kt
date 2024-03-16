package org.currytree.business

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.nio.file.Path

class RamValueStore<VALUE : Any>(val clazz: Class<VALUE>) : ValueStore<VALUE> {

    private val pathToBody = mutableMapOf<Path, String>()

    override fun write(path: Path, value: VALUE) {
        val valueString = Json.encodeToString(serializer(clazz), value)
        pathToBody[path] = valueString
    }

    override fun read(path: Path): VALUE {
        val bodyString = pathToBody.getOrElse(path) { throw BodyNotFound(path) }
        return clazz.cast(Json.decodeFromString(serializer(clazz), bodyString))
    }

    override fun exists(path: Path): Boolean {
        return pathToBody[path] != null
    }

}