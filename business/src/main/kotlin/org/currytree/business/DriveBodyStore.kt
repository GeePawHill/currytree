package org.currytree.business

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.currytree.PageBody
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import kotlin.io.path.exists

class DriveBodyStore(val root: Path) : BodyStore {
    override fun write(path: Path, body: PageBody) {
        val subpath = root.resolve(path)
        val bodyString = Json.encodeToString(body)
        try {
            Files.write(
                subpath,
                bodyString.toByteArray(Charset.defaultCharset()),
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE
            )
        } catch (wrapped: IOException) {
            throw RuntimeException(wrapped)
        }
    }

    override fun read(path: Path): PageBody {
        val subpath = root.resolve(path)
        try {
            val bodyString = Files.readString(subpath)
            return Json.decodeFromString(bodyString)
        } catch (e: NoSuchFileException) {
            throw BodyNotFound(subpath)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun exists(path: Path): Boolean {
        return root.resolve(path).exists()
    }
}