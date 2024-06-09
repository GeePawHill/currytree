package org.currytree.business.store

import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.currytree.business.ValueNotFound
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.exists

class DriveStore<VALUE : Any>(val root: Path, val clazz: Class<VALUE>) : Store<VALUE> {
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
            throw ValueNotFound(subpath)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override val size: Int
        get() = TODO("Not yet implemented")
}