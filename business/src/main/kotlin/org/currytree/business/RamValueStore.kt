package org.currytree.business

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlinx.serialization.serializer
import org.currytree.tree.BasicTree
import org.currytree.tree.Tree
import java.nio.file.Path

class RamValueStore<VALUE : Any>(val clazz: Class<VALUE>) : ValueStore<VALUE> {

    private val pathToBody = mutableMapOf<Path, String>()

    override fun write(path: Path, value: VALUE) {
        val valueString = format.encodeToString(serializer(clazz), value)
        pathToBody[path] = valueString
    }

    override fun read(path: Path): VALUE {
        val bodyString = pathToBody.getOrElse(path) { throw BodyNotFound(path) }
        return clazz.cast(format.decodeFromString(serializer(clazz), bodyString))
    }

    override fun exists(path: Path): Boolean {
        return pathToBody[path] != null
    }

    companion object {

        val format = Json {
            serializersModule = SerializersModule {
            }
        }
    }
}