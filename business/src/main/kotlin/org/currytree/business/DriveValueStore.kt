package org.currytree.business

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.exists

open class DriveValueStore<VALUE : Any>(val root: Path, val clazz: Class<VALUE>) : ValueStore<VALUE> {
    override fun exists(path: Path): Boolean {
        return root.resolve(path).exists()
    }

    override fun write(path: Path, value: VALUE) {
        val subpath = root.resolve(path)
        val valueString = Json.encodeToString(serializer(clazz), value)
        try {
            Files.write(
                subpath,
                valueString.toByteArray(Charset.defaultCharset()),
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE
            )
        } catch (wrapped: IOException) {
            throw RuntimeException(wrapped)
        }
    }

    override fun read(path: Path): VALUE {
        val subpath = root.resolve(path)
        try {
            val valueString = Files.readString(subpath)
            return clazz.cast(Json.decodeFromString(serializer(clazz), valueString))
        } catch (e: NoSuchFileException) {
            throw BodyNotFound(subpath)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}