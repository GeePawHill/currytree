package org.currytree.business

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(val username: String, val password: String, val email: String)
