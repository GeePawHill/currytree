package org.currytree.business


class UserRamStore(
    private val base: ValueStore<User> = RamValueStore(User::class.java)
) : ValueStore<User> by base