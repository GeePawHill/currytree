package org.currytree.business

import kotlinx.serialization.Serializable
import java.nio.file.Path

@Serializable
data class UserCredentials(val username: String, val password: String, val email: String) {
    val path: Path = Path.of(username)
}
