package org.currytree.business.store

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import org.currytree.business.ValueNotFound
import java.nio.file.Path

class RamStore<VALUE : Any>(val clazz: Class<VALUE>) : Store<VALUE> {

    private val pathToBody = mutableMapOf<Path, String>()

    override fun write(path: Path, value: VALUE) {
        val valueString = format.encodeToString(serializer(clazz), value)
        pathToBody[path] = valueString
    }

    override fun read(path: Path): VALUE {
        val bodyString = pathToBody.getOrElse(path) { throw ValueNotFound(path) }
        return clazz.cast(format.decodeFromString(serializer(clazz), bodyString))
    }

    override fun exists(path: Path): Boolean {
        return pathToBody[path] != null
    }

    companion object {

        val format = Json {
            serializersModule = SerializersModule {
                encodeDefaults = true
            }
        }
    }
}