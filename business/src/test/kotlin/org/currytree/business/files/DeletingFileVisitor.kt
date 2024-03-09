package org.currytree.business.files

import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

class DeletingFileVisitor(root: Path) : SimpleFileVisitor<Path>() {
    private val root: Path

    init {
        this.root = root.toAbsolutePath()
    }

    @Throws(IOException::class)
    override fun postVisitDirectory(dir: Path, exc: IOException): FileVisitResult {
        if (root !== dir) {
            dir.toFile().delete()
        }
        return FileVisitResult.CONTINUE
    }

    @Throws(IOException::class)
    override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
        file.toFile().delete()
        return FileVisitResult.CONTINUE
    }
}
