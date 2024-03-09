package org.currytree.business.files

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

class GitRootNotFound : RuntimeException("This test must be run inside a git repository.")

class TestFolder() {

    val root = testingTemp()

    fun destroy() {
        val deleter = DeletingFileVisitor(root)
        try {
            Files.walkFileTree(root, deleter)
            root.toFile().delete()
        } catch (wrapped: IOException) {
            throw RuntimeException("Deleting File Visitor failed for [$root].", wrapped)
        }
    }

    companion object {

        fun testingTemp(): Path {
            try {
                val gitRoot = requireGitRoot(Path.of("."))
                val testing = gitRoot.resolve("testing")
                Files.createDirectories(testing)
                return Files.createTempDirectory(testing, "test")
            } catch (wrapped: IOException) {
                throw RuntimeException(wrapped)
            }
        }

        fun requireGitRoot(from: Path): Path {
            var candidate = from.toAbsolutePath()
            while (candidate.nameCount > 1) {
                val candidateGit = candidate.resolve(".git")
                if (candidateGit.toFile().exists() && candidateGit.toFile().isDirectory()) return candidate
                candidate = candidate.resolve("..").toAbsolutePath().normalize()
            }
            throw GitRootNotFound()
        }
    }
}