package org.currytree.business

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

enum class Ability {
    canEdit,
    canView
}

data class Registration(val userId: String, val cohortId: String, val abilities: Set<Ability>)

class RamRegistrar() {
    val userIds = mutableSetOf<String>()
    val cohortIds = mutableSetOf<String>()
    val registrations = mutableSetOf<Registration>()

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

    fun allCohorts(): Set<String> {
        return cohortIds
    }

    fun getUserRegistrations(userId: String): List<Registration> {
        return registrations.filter { registration -> registration.userId == userId }.toList()
    }

    fun addCohort(cohortId: String): Boolean {
        cohortIds.add(cohortId)
        return true
    }

    fun register(userId: String, cohortId: String, abilities: Set<Ability>) {
        val new = Registration(userId, cohortId, abilities)
        registrations.add(new)
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
        registrar.addUser("geepaw")
        assertThat(registrar.hasUser("geepaw")).isTrue()
    }

    @Test
    fun `getUserRegistrations with non-existent user`() {
        assertThat(registrar.getUserRegistrations("geepaw")).isEmpty()
    }

    @Test
    fun `getUserRegistrations with existing unregisterd user`() {
        registrar.addUser("geepaw")
        assertThat(registrar.getUserRegistrations("geepaw")).isEmpty()
    }

    @Test
    fun `no cohorts`() {
        assertThat(registrar.allCohorts()).isEmpty()
    }

    @Test
    fun `add cohort`() {
        assertThat(registrar.addCohort("some-cohort")).isTrue()
        assertThat(registrar.allCohorts()).containsExactly("some-cohort")
    }

    @Test
    fun `addExistingStudentToExistingCohort`() {
        registrar.addUser("geepaw")
        registrar.addCohort("some-cohort")
        registrar.register("geepaw", "some-cohort", setOf(Ability.canView))
        assertThat(registrar.getUserRegistrations("geepaw")).containsExactly(
            Registration("geepaw", "some-cohort", setOf(Ability.canView))
        )
    }
}