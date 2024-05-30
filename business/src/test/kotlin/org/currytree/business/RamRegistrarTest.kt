package org.currytree.business

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RamRegistrar() {
    val userIds = mutableSetOf<String>()

    fun allUsers(): Set<String> {
        return userIds
    }

    fun hasUser(userId: String): Boolean {
        return userIds.contains(userId)
    }

    fun addUser(userId: String): Boolean {
        userIds.add(userId)
        return true
    }

    fun removeUser(userId: String): Boolean {
        return userIds.remove(userId)
    }


    fun allCohorts(): List<String> {
        return emptyList()
    }

}

class RamRegistrarTest {

    val registrar = RamRegistrar()

    @Test
    fun `no users`() {
        assertThat(registrar.allUsers()).isEmpty()
    }

    @Test
    fun `add user`() {
        assertThat(registrar.addUser("geepaw")).isTrue()
        assertThat(registrar.allUsers()).containsExactly("geepaw")
    }

    @Test
    fun `remove user`() {
        registrar.addUser("geepaw")
        assertThat(registrar.removeUser("geepaw")).isTrue()
    }

    @Test
    fun `has user by id`() {
        assertThat(registrar.hasUser("geepaw")).isFalse()
        assertThat(registrar.addUser("geepaw")).isTrue()
        assertThat(registrar.hasUser("geepaw")).isTrue()
    }

    @Test
    fun `no cohorts`() {
        assertThat(registrar.allCohorts()).isEmpty()
    }
}